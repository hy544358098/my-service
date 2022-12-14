package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 营销预算执行申请--预算执行明细实体
 * @Author YANKAIBO
 * @Date 2021/11/30 15:07
 * @Version 1.0
 */
@Data
public class BudgetExecutionDetailsData {
    @ApiModelProperty("一级科目")
    public String km1;

    @ApiModelProperty("二级科目")
    public String km2;

    @ApiModelProperty("三级科目")
    public String km3;

    @ApiModelProperty("获客渠道")
    public String qd;

    @ApiModelProperty("说明")
    public String sm;

    @ApiModelProperty("本次申请金额（元）")
    public String je;

    @ApiModelProperty("业态")
    public String yt;

    @ApiModelProperty("品类")
    public String pl;
}
