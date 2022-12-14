package com.crc.runwork.core.ssdp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 描述: ssdp系统网关公共参数vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-08-28 17:43
 */
public class SsdpSysApiVo extends BaseSsdpApiVo {

    /**请求报文的签名字符串*/
    private String Sign;
    /**分配给调用方的组织编码（API开放平台提供）*/
    private String Sys_ID;


    @JSONField(name = "Sign")
    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    @JSONField(name = "Sys_ID")
    public String getSys_ID() {
        return Sys_ID;
    }

    public void setSys_ID(String sys_ID) {
        Sys_ID = sys_ID;
    }
}
