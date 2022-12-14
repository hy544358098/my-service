package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.independent.model.vo.request.TLdapUserPO;
import com.crc.business.independent.model.vo.result.TLdUserCityVo;
import com.crc.business.independent.model.vo.result.TLdapUserVo;
import com.crc.business.independent.service.ITLdapUserService;
import com.crc.common.utils.UserInformationUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.crc.business.oa.service.impl.CommonServiceImpl.DEPT_MAP;

/** @author xiaoming */
@Service
public class TLdapUserServiceImpl extends ServiceImpl<TLdapUserDao, TLdapUser>
        implements ITLdapUserService {

  @Autowired private TLdapUserDao tLdapUserDao;


  @Override
  public TLdUserCityVo getUserInfo(TLdapUserPO tLdapUserPO) {
    // 条件查询
    QueryWrapper<TLdapUser> tlWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(tLdapUserPO.getUsrLogin())) {
      tlWrapper.eq("usr_login", tLdapUserPO.getUsrLogin());
    }
    if (StringUtils.isNotEmpty(tLdapUserPO.getUsrName())) {
      tlWrapper.eq("usr_name", tLdapUserPO.getUsrName());
    }

    TLdapUser tLdapUser = tLdapUserDao.selectOne(tlWrapper);
    TLdUserCityVo tLdUserCityVo = new TLdUserCityVo();
    BeanUtils.copyProperties(tLdapUser, tLdUserCityVo);
    //私密信息剔除
    tLdUserCityVo.setUsrEmail(null);
    tLdUserCityVo.setUsrPassword(null);
    tLdUserCityVo.setUsrMobile(null);
    tLdUserCityVo.setActName(UserInformationUtil.subString(tLdapUser.getActName()));
    // 设置城市公司
    for (String cityName : DEPT_MAP.keySet()) {
      if (StringUtils.contains(tLdapUser.getActName(), cityName)) {
        tLdUserCityVo.setCityFirm(DEPT_MAP.get(cityName));
        break;
      }
    }
    return tLdUserCityVo;
  }

  @Override
  public Page<TLdapUser> getUserList(TLdapUserPO tLdapUserPO, String userId) {
    Page<TLdapUser> page = new Page<>(tLdapUserPO.getPageNum(), tLdapUserPO.getPageSize());
    page = tLdapUserDao.list(page, tLdapUserPO);
    List<TLdapUser> tLdapUsers = page.getRecords();
    // 过滤当前登录人信息
    tLdapUsers =
            tLdapUsers.stream()
                    .filter(tLdapUser -> !StringUtils.equals(tLdapUser.getUsrLogin(), userId))
                    .collect(Collectors.toList());
    //私密信息剔除
    tLdapUsers.stream().forEach(vo->{
      vo.setActName(UserInformationUtil.subString(vo.getActName()));
      vo.setUsrPassword(null);
      vo.setUsrEmail(null);
      vo.setUsrMobile(null);
    });
    page.setRecords(tLdapUsers);
    return page;
  }

  @Override
  public List<TLdapUserVo> getUserByOrgCode(TLdapUserPO tLdapUserPO) {
    // 条件查询
    QueryWrapper<TLdapUser> tlWrapper = new QueryWrapper<>();
    if (null != tLdapUserPO.getOrgCode()) {
      tlWrapper.eq("usr_udf_deptid", tLdapUserPO.getOrgCode());
    }
    List<TLdapUser> tLdapUsers = tLdapUserDao.selectList(tlWrapper);
    List<TLdapUserVo> tLdapUserList = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(tLdapUsers)) {
      tLdapUsers.forEach(
              tLdapUser -> {
                TLdapUserVo tLdapUserVo = new TLdapUserVo();
                BeanUtils.copyProperties(tLdapUser, tLdapUserVo);
                tLdapUserList.add(tLdapUserVo);
              });
    }
    return tLdapUserList;
  }
}
