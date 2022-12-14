package com.x.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MySupervisionCountVo {
    @ApiModelProperty("总数")
    private int total;

    @ApiModelProperty("驳回")
    private int reject;

    @ApiModelProperty("待审批")
    private int  approval;

    @ApiModelProperty("待办理")
    private int  untreated;

    @ApiModelProperty("已转办")
    private int transfer;

    @ApiModelProperty("已反馈")
    private int feedback;

    @ApiModelProperty("已办结")
    private int finish;
}
