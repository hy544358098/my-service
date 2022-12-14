package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

@Data
public class OrgInfoDO {
    private String id;
    private String orgName;
    private String orgCode;
    private String parentId;
    private String orgType;
    private String orgRange;
}
