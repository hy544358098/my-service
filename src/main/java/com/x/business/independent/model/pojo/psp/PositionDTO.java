package com.x.business.independent.model.pojo.psp;

import lombok.Data;

import java.util.List;

@Data
public class PositionDTO {
    private String id;
    private String parentId;
    private String applicationId;
    private String orgId;
    private String name;
    private String primaryPostnFlag;
    private String remarks;
    private String delFlag;
    private OrgResDTO positionOrg;
    private List<PositionUdoEasyDO> positionUdos;
}
