package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ShortListSourceData
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 9:11
 * @Version: 1.0
 */
@Data
public class PurchaseShortListSourceData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("组织")
    private String organization;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("资格预审结果头-资格预审单号")
    private String advanceNumber;

    @ApiModelProperty("资格预审结果头-合约分判项")
    private String contractSort;

    @ApiModelProperty("资格预审结果头-采购方案名称")
    private String purchaseSortName;

    @ApiModelProperty("资格预审结果头-采购方案编号")
    private String purchaseSortNumber;

    @ApiModelProperty("资格预审结果头-公告标题")
    private String bulletinTitle;

    @ApiModelProperty("资格预审结果头-公告编号")
    private String bulletinNumber;

    @ApiModelProperty("资格预审结果头-采购组织")
    private String purchaseOrganization;

    @ApiModelProperty("资格预审结果头-采购方式")
    private String purchaseType;

    @ApiModelProperty("资格预审结果头-报名有效期")
    private String signUpEfficientTime;

    @ApiModelProperty("资格预审结果头-备注")
    private String remark;

    @ApiModelProperty("资格预审结果头-经办人")
    private String preTrialOperatorName;

    @ApiModelProperty("供方引进头-标题")
    private String supplierHeadTital;

    @ApiModelProperty("供方引进头-申请部门")
    private String supplierHeadApplyDept;

    @ApiModelProperty("供方引进头-备注")
    private String supplierHeadRemark;

    @ApiModelProperty("供方引进头-组织")
    private String supplierHeadOrganization;

    @ApiModelProperty("供方引进头-座机号")
    private String supplierHeadLandline;

    @ApiModelProperty("供方引进头-经办人")
    private String supplierHeadOperatorName;

    @ApiModelProperty("供方引进头-联系方式")
    private String supplierHeadConnect;

    @ApiModelProperty("单据备注")
    private String supplierResultRemark;

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
