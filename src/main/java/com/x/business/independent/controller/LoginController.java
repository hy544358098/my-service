package com.x.business.independent.controller;

import com.x.common.enums.ErrorCodeEnum;
import com.x.business.independent.service.LoginService;
import com.x.common.exception.ServiceException;
import com.x.runwork.core.ssdp.ResponseData;
import com.x.runwork.core.ssdp.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录服务
 *
 * @author xiaoming
 * @since 2021-09-20
 */
@Api(tags = "登录服务")
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/ssoLogin")
    public String ssoLogin(HttpServletRequest request) {
        try {
            // 获取请求头的用户名
            String uid = request.getHeader("ldapuid");
            log.info("ldapuid:{}", uid);
            if (StringUtils.isEmpty(uid)) {
                throw new ServiceException(ErrorCodeEnum.LOGIN_USER_AUTH_FAILED);
            }
            // sso登录处理
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , loginService.ldapSsoLogin(uid)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
