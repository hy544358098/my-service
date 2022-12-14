package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

@Data
public class PspOrgTreeReq {
    /***
     * 组织类型
     */
    private String orgType;

    /***
     * 组织编码
     */
    private String orgCode;
}
