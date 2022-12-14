package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: ShortListVo
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:55
 * @Version: 1.0
 */
@Data
public class PurchaseShortListedVO {
    @ApiModelProperty("编号")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("组织")
    private String organization;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("资格预审单号")
    private String advanceNumber;

    @ApiModelProperty("合约分判项")
    private String contractSort;

    @ApiModelProperty("采购方案名称")
    private String purchaseSortName;

    @ApiModelProperty("采购方案编号")
    private String purchaseSortNumber;

    @ApiModelProperty("公告标题")
    private String bulletinTitle;

    @ApiModelProperty("公告编号")
    private String bulletinNumber;

    @ApiModelProperty("采购组织")
    private String purchaseOrganization;

    @ApiModelProperty("采购方式")
    private String purchaseType;

    @ApiModelProperty("报名有效期")
    private String signUpEfficientTime;

    @ApiModelProperty("备注")
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

    @ApiModelProperty("审批状态: approval 待审批；finish 审批完成")
    private String approvalStatus;

    @ApiModelProperty("是否已阅: read 已阅 ；beread 待阅")
    private String hasRead;

    @ApiModelProperty("观察状态:special_attention 特别关注； warning 预警； normal 正常")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点：untreated 待办理； transfer 已转办； feedback 已反馈；finish 已办结")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("操作人节点信息")
    private String operationNodeName;

    @ApiModelProperty("子表单信息")
    private String tableInfo;
}
