package com.x.business.independent.model.pojo.psp;

import lombok.Data;

import java.util.List;

@Data
public class UdoTypeInfoDTO {
    private String id;
    private String applicationId;
    private String name;
    private String remarks;
    private List<UdoEasyInfoDO> udoList;
}
