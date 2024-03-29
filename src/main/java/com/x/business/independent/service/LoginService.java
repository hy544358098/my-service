package com.x.business.independent.service;

import com.x.business.independent.model.vo.request.LoginRequest;
import com.x.business.independent.model.vo.result.ResultVo;

/**
 * <p>
 *  登录接口
 * </p>
 *
 * @author xiaoming
 * @since 2021-09-20
 */
public interface LoginService{

    ResultVo authentication(LoginRequest loginRequest);

    ResultVo ldapSsoLogin(String loginUid);
}
