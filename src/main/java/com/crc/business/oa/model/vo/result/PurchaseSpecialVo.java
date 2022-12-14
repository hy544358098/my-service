package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseSpecialVo {

    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("主表uuid")
    private String uid;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("所属大区/公司")
    private String cityFirm;

    @ApiModelProperty("所属部门/项目")
    private String originalDep;

    @ApiModelProperty("采购分类")
    private String purchasingType;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("预算金额")
    private String estimatedAmount;

    @ApiModelProperty("过程描述")
    private String processDescription;

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
