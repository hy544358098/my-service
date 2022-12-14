package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

import java.util.List;

@Data
public class RoleInfoDTO {
    private String id;
    private String applicationId;
    private String name;
    private String type;
    private String remarks;
    //授权的组织
    private List<OrgInfoDO> orgs;
    //授权的自定义
    private List<UdoTypeInfoDTO> udoTypes;
    //授权的菜单
    private List<MenuDO> menus;
}
