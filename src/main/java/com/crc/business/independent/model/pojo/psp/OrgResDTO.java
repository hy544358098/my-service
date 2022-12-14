package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

import java.util.List;

@Data
public class OrgResDTO {
    private String id;
    private String parentId;
    private String applicationId;
    private String orgName;
    private String orgCode;
    private String orgType;
    private String orgGrade;
    private String projectType;
    private String address;
    private String postalCode;
    private String master;
    private String phone;
    private String fax;
    private String email;
    private String remarks;
    private String parentName;
    private String level;
    List<OrgResDTO> children;
}
