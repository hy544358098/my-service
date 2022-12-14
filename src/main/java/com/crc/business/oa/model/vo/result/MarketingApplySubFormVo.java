package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 营销预算执行申请子表单统计实体
 * @Author YANKAIBO
 * @Date 2021/11/30 14:40
 * @Version 1.0
 */
@Data
public class MarketingApplySubFormVo {
    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("预算编制月")
    private String budgetMonth;

    @ApiModelProperty("一级科目")
    private String km1;

    @ApiModelProperty("本次申请金额")
    private double thisAmount;

    @ApiModelProperty("动态表格内容")
    private String tableInfo;

    @ApiModelProperty("审核状态")
    private String approvalStatus;

}
