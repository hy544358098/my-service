package com.x.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/17 15:23
 * @Version 1.0
 */
@Data
public class StatusCount {
    @ApiModelProperty("待办理")
    private Integer untreated;
    @ApiModelProperty("待反馈")
    private Integer transfer;
    @ApiModelProperty("已反馈")
    private Integer feedback;
    @ApiModelProperty("已办结")
    private Integer finish;
}
