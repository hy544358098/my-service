package com.x.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/2 15:38
 * @Version 1.0
 */
@Data
public class PendingVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("业务流程")
    private String tableName;

    @ApiModelProperty("状态")
    private String alertNode;

    @ApiModelProperty("申请人")
    private String operatorName;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("操作人id")
    private String operationPeopleId;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;

    @ApiModelProperty("操作时间")
    private String operationTime;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警人")
    private String warningPeople;
}
