package com.crc.runwork.core.ssdp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 描述: ssdp云网关公共参数vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-08-28 17:43
 */
public class SsdpCloudApiVo extends BaseSsdpApiVo {

    /**请求报文的签名字符串*/
    @JSONField(name = "Sign")
    private String Sign;

    /**分配给调用方的系统编码（API开放平台提供）*/
    @JSONField(name = "Sys_ID")
    private String Sys_ID;


    public String getSys_ID() {
        return Sys_ID;
    }

    public void setSys_ID(String sys_ID) {
        Sys_ID = sys_ID;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
}
