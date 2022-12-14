package com.crc.business.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.independent.dao.OperationDetailDao;
import com.crc.business.independent.dao.SupervisionDao;
import com.crc.business.independent.model.entity.Supervision;
import com.crc.business.oa.dao.OaOperateDao;
import com.crc.business.oa.dao.PendingDao;
import com.crc.business.oa.model.vo.request.PendingParam;
import com.crc.business.oa.model.vo.result.InformVo;
import com.crc.business.oa.model.vo.result.PendingVo;
import com.crc.business.oa.service.CommonService;
import com.crc.business.oa.service.PendingService;
import com.crc.common.constant.OaConstant;
import com.crc.common.utils.UserInformationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/3 15:53
 * @Version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PendingServiceImpl implements PendingService {

    private final PendingDao pendingDao;
    private final OaOperateDao oaOperateDao;
    private final OperationDetailDao operationDetailDao;
    private final CommonService commonService;
    private final SupervisionDao supervisionDao;
    public static final String PENDING = "pending";
    public static final String ALREADY = "already";
    public static final String PERSONAL = "personal";
    public static final String YES = "yes";

    @Override
    public Page<PendingVo> list(PendingParam pendingParam,String roleNames) {
        Page<PendingVo> page =
                new Page<>(pendingParam.getPageNum(), pendingParam.getPageSize());
        String roleName="";
        try {
           roleName = URLDecoder.decode(roleNames, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("decode failed:{}", e.getMessage());
        }
        //特殊角色处理
        if (roleName.contains(UserInformationUtil.ROLE_NAME_REGIONAL_AUDIT)
                ||roleName.contains(UserInformationUtil.ROLE_NAME_REGIONAL_BUSINESS)
                ||roleName.contains(UserInformationUtil.ROLE_NAME_REGIONAL_STAFF)){
            pendingParam.setRoleName(UserInformationUtil.ROLE_NAME_REGIONAL_STAFF);
        } else {
            pendingParam.setRoleName(commonService.getRoleName(roleNames));
        }
        if (StringUtils.equals(pendingParam.getType(),PENDING)){
            //非大区观察员，待办信需要查询是否流程在本人处
            if (!StringUtils.equals(pendingParam.getRoleName(),
                    OaConstant.ROLE_NAME_REGIONAL_SUPERVISOR)){
                List<String> personalList=new ArrayList<>();
                List<String> focusList=new ArrayList<>();
                List<String> competence =new ArrayList<>();
                List<String> personal =new ArrayList<>();
                List<PendingVo> pending = pendingDao.pending(pendingParam);
                if (StringUtils.equals(pendingParam.getRoleName(),
                        UserInformationUtil.ROLE_NAME_SPECIAL)){
                    for (PendingVo vo:pending){
                        if (StringUtils.equals(vo.getTableName(),PERSONAL)){
                            personalList.add(vo.getUid());

                        }else {
                            competence.add(vo.getUid());
                        }
                    }
                }
                pending.stream().forEach(vo->{
                    if (StringUtils.equals(vo.getTableName(),PERSONAL)){
                        personalList.add(vo.getUid());
                    }else {
                        focusList.add(vo.getUid());
                    }
                });
                if (CollectionUtil.isNotEmpty(focusList)){
                    competence = oaOperateDao.findCompetenceNew(focusList,pendingParam.getLdapId());
                }
                if (CollectionUtil.isNotEmpty(personalList)){
                    personal = operationDetailDao.findCompetenceNew(personalList,pendingParam.getLdapId());
                }
                for (String vo: personal) {
                    competence.add(vo);
                }
                if (CollectionUtil.isEmpty(competence)){
                    return page;
                }
                pendingParam.setUidList(competence);
                page = pendingDao.findCompetence(page, pendingParam);
            }else {
                page = pendingDao.pending(page,pendingParam);
            }
        }else {
            page = pendingDao.already(page,pendingParam);
        }
        // 大区员工的匿名数据 置空申请人字段(不是自己提交的数据都匿名)
        if (StringUtils.equals(pendingParam.getRoleName(), OaConstant.ROLE_NAME_REGIONAL_STAFF)) {
            List list = new ArrayList<PendingVo>();
            for (PendingVo vo : page.getRecords()) {
                if (StringUtils.equals(vo.getTableName(), PERSONAL)) {
                    Supervision supervision =
                            supervisionDao.selectOne(new QueryWrapper<Supervision>().eq("id", vo.getUid()));
                    if (!StringUtils.equals(pendingParam.getLdapId(), supervision.getSubmitPeopleId())) {
                        vo.setOperatorName(null);
                    }
                }
                list.add(vo);
            }
            page.setRecords(list);
        }
        return page;
    }


    @Override
    public Page<InformVo> informList(PendingParam pendingParam) {
        Page<PendingVo> page =
                new Page<>(pendingParam.getPageNum(), pendingParam.getPageSize());
        Page<InformVo> informVoPage = pendingDao.informList(page, pendingParam);
        return informVoPage;
    }
}
