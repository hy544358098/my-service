package com.crc.business.independent.model.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoming
 * @Description TODO
 * @date 2021/9/17 20:07
 */
@Data
public class LoginRequest implements Serializable {

    /**
     * 登录id
     */
    private String userId;

    /**
     * 登录密码
     */
    private String userPassword;
}
