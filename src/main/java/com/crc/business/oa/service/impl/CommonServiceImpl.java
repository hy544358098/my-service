package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.business.independent.dao.SettingParameterDao;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.model.vo.request.SettingParamSearch;
import com.crc.business.independent.model.vo.result.SettingParameterListVo;
import com.crc.business.oa.dao.ActiveFormDao;
import com.crc.business.oa.dao.AppendixDao;
import com.crc.business.oa.dao.ApprovalDao;
import com.crc.business.oa.dao.DepartmentDao;
import com.crc.business.oa.model.entity.DepartmentEntity;
import com.crc.business.oa.service.CommonService;
import com.crc.common.enums.BusinessProcessEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.crc.common.constant.OaConstant.*;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {
    @Autowired
    private AppendixDao appendixDao;
    @Autowired
    private ApprovalDao approvalDao;
    @Autowired
    private ActiveFormDao tOaActiveFormDao;
    @Autowired
    private TLdapUserDao ldapUserDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private SettingParameterDao parameterDao;

    /**
     * 用于缓存城市公司
     */
    public static Map<String, String> DEPT_MAP = new LinkedHashMap<>();

    /**
     * 存储各流程新增数据的总量，用于计算抽取比例
     */
    public static ConcurrentHashMap<String, Integer> countMap = new ConcurrentHashMap();

    /**
     * 存储各流程设置的抽取比例
     */
    public static ConcurrentHashMap<String, Integer> extractMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // 初始化城市公司
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<DepartmentEntity> departmentEntities = departmentDao.selectList(queryWrapper);
        departmentEntities.stream()
                .forEach(
                        departmentEntity -> {
                            DEPT_MAP.put(departmentEntity.getShortDeptName(), departmentEntity.getDeptName());
                        });

        // 初始化各流程设置的列表抽取比例
        SettingParamSearch settingParamSearch = new SettingParamSearch();
        settingParamSearch.setType(
                BusinessProcessEnum.ConditionType.STATUS_EXTRACT_PROPORTION.getCode());
        List<SettingParameterListVo> list = parameterDao.getListByType(settingParamSearch);
        list.stream()
                .forEach(
                        settingParameterListVo -> {
                            extractMap.put(
                                    settingParameterListVo.getTypeCode(), settingParameterListVo.getExtractAmount());
                        });
    }

    /***
     * @Description 获取用户角色
     * @Param roleNames
     * @return roleName
     */
    @Override
    public String getRoleName(String roleNames) {
        // 默认都是大区员工角色
        String roleName = ROLE_NAME_REGIONAL_STAFF;
        if (StringUtils.isNotEmpty(roleNames)) {
            try {
                roleNames = URLDecoder.decode(roleNames, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error("decode failed:{}", e.getMessage());
            }
            if (StringUtils.containsAny(
                    roleNames, ROLE_NAME_REGIONAL_SUPERVISOR, ROLE_NAME_STATISTICS_SUPERVISOR)) {
                roleName = ROLE_NAME_REGIONAL_SUPERVISOR;
            }
        }
        log.info("role name is : {}", roleName);
        return roleName;
    }
}
