package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 营销年度预算调整实体
 * @Author: xiaoming
 * @Date: 2021/10/29 17:15
 * @Version: 1.0
 */
@Data
public class MarketingAnnualBudgetData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("预算名称")
    private String budgetName;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("城市公司")
    private String cityFirm;

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

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
