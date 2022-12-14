package com.x.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 营销年度预算调整实体
 * @Author: xiaoming
 * @Date: 2021/10/29 17:15
 * @Version: 1.0
 */
@Data
@TableName("t_oa_marketing_annual_budget")
public class MarketingAnnualBudgetEntity extends BaseEntity<MarketingAnnualBudgetEntity>{
    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("预算名称")
    private String budgetName;

    @ApiModelProperty("申请人")
    private String applyName;

    @ApiModelProperty("所属大区")
    private String region;

    @ApiModelProperty("预算归口部门")
    private String budgetDept;

    @ApiModelProperty("预算年度")
    private String budgetAmount;

    @ApiModelProperty("项目投批费用")
    private String approvalFee;

    @ApiModelProperty("项目投批签约额")
    private String approvalContractAmount;

    @ApiModelProperty("项目投批费用率")
    private String approvalFeeRate;

    @ApiModelProperty("说明")
    private String remark;
}
