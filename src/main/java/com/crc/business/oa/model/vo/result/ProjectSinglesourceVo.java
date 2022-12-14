package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectSinglesourceVo {

    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("UUID")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("所属部门/项目")
    private String originalDep;

    @ApiModelProperty("发起部门")
    private String initiateDep;

    @ApiModelProperty("经办人姓名")
    private String operatorName;

    @ApiModelProperty("所属采购条线")
    private String ownedPurchasLine;

    @ApiModelProperty("采购分类")
    private String purchasingType;

    @ApiModelProperty("符合单一来源条款")
    private String singleSourceClause;

    @ApiModelProperty("计价模式")
    private String pricingModel;

    @ApiModelProperty("采购预估金额")
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

    @ApiModelProperty("过程描述")
    private String processDescription;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;
}
