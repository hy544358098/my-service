package com.crc.runwork.core.ssdp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 描述: ssdp移动网关公共参数vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-08-28 17:43
 */
public class BaseSsdpApiVo implements Serializable {

    /**调用方的应用编码（API开放平台提供）*/
    private String App_Sub_ID;

    /**调用方应用授权令牌（API开放平台提供）*/
    private String App_Token;

    /**被调用的API编码（API开放平台提供）*/
    private String Api_ID;

    /**被调用的API版本号（API开放平台提供）*/
    private String Api_Version;

    /**调用方时间戳，格式为yyyy-mm-dd HH:mm:ss:SSS*/
    private String Time_Stamp;

    /**调用方移动用户的身份令牌（API开放平台提供*/
    private String User_Token;

    /**分配给调用方的组织编码（API开放平台提供）*/
    private String Partner_ID;


    @JSONField(name = "App_Sub_ID")
    public String getApp_Sub_ID() {
        return App_Sub_ID;
    }

    public void setApp_Sub_ID(String app_Sub_ID) {
        App_Sub_ID = app_Sub_ID;
    }

    @JSONField(name = "App_Token")
    public String getApp_Token() {
        return App_Token;
    }

    public void setApp_Token(String app_Token) {
        App_Token = app_Token;
    }

    @JSONField(name = "Api_ID")
    public String getApi_ID() {
        return Api_ID;
    }

    public void setApi_ID(String api_ID) {
        Api_ID = api_ID;
    }

    @JSONField(name = "Api_Version")
    public String getApi_Version() {
        return Api_Version;
    }

    public void setApi_Version(String api_Version) {
        Api_Version = api_Version;
    }

    @JSONField(name = "Time_Stamp")
    public String getTime_Stamp() {
        return Time_Stamp;
    }

    public void setTime_Stamp(String time_Stamp) {
        Time_Stamp = time_Stamp;
    }

    @JSONField(name = "User_Token")
    public String getUser_Token() {
        return User_Token;
    }

    public void setUser_Token(String user_Token) {
        User_Token = user_Token;
    }

    @JSONField(name = "Partner_ID")
    public String getPartner_ID() {
        return Partner_ID;
    }

    public void setPartner_ID(String partner_ID) {
        Partner_ID = partner_ID;
    }



}
