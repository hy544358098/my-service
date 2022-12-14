package com.crc.runwork.core.ssdp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 描述: ssdp移动网关公共参数vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-08-28 17:43
 */
public class SsdpAppApiVo extends BaseSsdpApiVo {

    /**请求报文的签名字符串*/
    @JSONField(name = "Sign")
    private String Sign;
    /**调用方移动应用编码*/
    @JSONField(name = "App_ID")
    private String App_ID;
    /**调用方移动应用版本*/
    @JSONField(name = "App_Version")
    private String App_Version;
    /**调用方移动设备编号*/
    @JSONField(name = "Divice_ID")
    private String Divice_ID;
    /**调用方移动设备品牌与型号*/
    @JSONField(name = "Divice_Version")
    private String Divice_Version;
    /**调用方移动设备操作系统名称与版本*/
    @JSONField(name = "OS_Version")
    private String OS_Version;


    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }


    public String getApp_ID() {
        return App_ID;
    }

    public void setApp_ID(String app_ID) {
        App_ID = app_ID;
    }


    public String getApp_Version() {
        return App_Version;
    }

    public void setApp_Version(String app_Version) {
        App_Version = app_Version;
    }


    public String getDivice_ID() {
        return Divice_ID;
    }

    public void setDivice_ID(String divice_ID) {
        Divice_ID = divice_ID;
    }


    public String getDivice_Version() {
        return Divice_Version;
    }

    public void setDivice_Version(String divice_Version) {
        Divice_Version = divice_Version;
    }


    public String getOS_Version() {
        return OS_Version;
    }

    public void setOS_Version(String OS_Version) {
        this.OS_Version = OS_Version;
    }

}
