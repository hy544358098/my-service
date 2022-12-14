package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DesignChangeParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("大区")
    private String region;

    @ApiModelProperty("项目")
    private String project;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("紧急程度")
    private String emergencyLevel;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅")
    private String hasRead;
}
