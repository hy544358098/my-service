package com.x.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InformVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("申请人")
    private String operatorName;

    @ApiModelProperty("业务流程")
    private String tableName;

    @ApiModelProperty("状态")
    private String alertNode;

    @ApiModelProperty("操作人id")
    private String operationPeopleId;

    @ApiModelProperty("操作人")
    private String operationPeople;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;

    @ApiModelProperty("被操作人")
    private String byOperationPeople;

    @ApiModelProperty("操作时间")
    private String operationTime;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警人")
    private String warningPeople;
}
