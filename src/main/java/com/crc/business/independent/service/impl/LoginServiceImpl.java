package com.crc.business.independent.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.independent.model.pojo.psp.PspBaseRes;
import com.crc.business.independent.model.vo.request.LoginRequest;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.LoginService;
import com.crc.business.independent.thirdservice.PspInfoService;
import com.crc.common.utils.ResultVoUtil;
import com.crc.common.utils.SHA256;
import com.crc.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录实现类
 *
 * @author xiaoming
 * @since 2021-09-20
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

  @Autowired private TLdapUserDao tLdapUserDao;

  @Autowired private PspInfoService pspInfoService;

  @Override
  public ResultVo authentication(LoginRequest loginRequest) {
    if (ObjectUtils.isEmpty(loginRequest)) {
      throw new ServiceException(ErrorCodeEnum.PARAM_ERROR);
    }
    List<TLdapUser> usrLists =
        tLdapUserDao.selectList(
            new QueryWrapper<TLdapUser>().eq("usr_login", loginRequest.getUserId()));
    if (CollectionUtil.isEmpty(usrLists)) {
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_NOT_EXIT);
    }
    if (usrLists.size() > 1) {
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_REPEAT);
    }
    String authCode = SHA256.sha256(loginRequest.getUserPassword());
    String pwd = usrLists.get(0).getUsrPassword();

    // 用户鉴权判断
    if (!StringUtils.equals(authCode, pwd)) {
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_AUTH_FAILED);
    }

    // 获取用户权限数据
    PspBaseRes userInfo = pspInfoService.getUserInfo(loginRequest.getUserId());
    return ResultVoUtil.success(userInfo.getData());
  }

  @Override
  public ResultVo ldapSsoLogin(String loginUid) {
    if (StringUtils.isEmpty(loginUid)) {
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_AUTH_FAILED);
    }
    List<TLdapUser> usrLists =
        tLdapUserDao.selectList(new QueryWrapper<TLdapUser>().eq("usr_login", loginUid));
    if (CollectionUtil.isEmpty(usrLists)) {
      log.error("user not exit");
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_NOT_EXIT);
    }

    // 获取用户权限数据
    PspBaseRes userInfo = pspInfoService.getUserInfo(loginUid);
    return ResultVoUtil.success(userInfo.getData());
  }
}
