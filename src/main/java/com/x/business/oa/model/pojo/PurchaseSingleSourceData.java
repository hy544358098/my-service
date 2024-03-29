package com.x.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/25 14:29
 * @Version 1.0
 */
@Data
public class PurchaseSingleSourceData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("所属大区/公司")
    private String originalRegion;

    @ApiModelProperty("所属部门/项目")
    private String originalDep;

    @ApiModelProperty("经办人姓名")
    private String operatorName;

    @ApiModelProperty("所属采购条线")
    private String ownedPurchasLine;

    @ApiModelProperty("采购分类")
    private String purchasingType;

    @ApiModelProperty("发起部门")
    private String initiateDep;

    @ApiModelProperty("符合单一来源条款")
    private String singleSourceClause;

    @ApiModelProperty("计价模式")
    private String pricingModel;

    @ApiModelProperty("采购预估金额（元）")
    private String estimatedAmount;

    @ApiModelProperty("采购模式")
    private String procurementModel;

    @ApiModelProperty("按制度应执行的采购方式")
    private String sysForPurchasingMethod;

    @ApiModelProperty("变更后的采购方式")
    private String changedPurchasingMethod;

    @ApiModelProperty("甲方单位")
    private String partyA;

    @ApiModelProperty("乙方单位")
    private String partyB;

    @ApiModelProperty("合同预计执行时间")
    private String contractExecutionTime;

    @ApiModelProperty("过程描述（含市场调研、会议洽谈情况）")
    private String processDescription;

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
