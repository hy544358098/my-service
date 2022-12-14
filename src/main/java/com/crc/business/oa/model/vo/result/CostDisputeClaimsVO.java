package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/** @Description: 成本合约—工程争议索赔 @Date: 2021/10/12 14:55 @Version: 1.0 */
@Data
public class CostDisputeClaimsVO {
  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("简易的UUID，确保唯一，关联其它子表")
  private String uid;

  @ApiModelProperty("单据编号")
  private String documentNumber;

  @ApiModelProperty("申请日期")
  private String applyDate;

  @ApiModelProperty("城市公司")
  private String cityFirm;

  @ApiModelProperty("经办人")
  private String operatorName;

  @ApiModelProperty("合同名称")
  private String contractTitle;

  @ApiModelProperty("合同方式")
  private String contractStyle;

  @ApiModelProperty("合同计价方式")
  private String contractPricingMethod;

  @ApiModelProperty("对应公司")
  private String forCompany;

  @ApiModelProperty("合同类别")
  private String contractType;

  @ApiModelProperty("费用类别")
  private String costType;

  @ApiModelProperty("供应商")
  private String supplier;

  @ApiModelProperty("合同金额")
  private String contractAmount;

  @ApiModelProperty("甲方主张金额")
  private String partyAAmount;

  @ApiModelProperty("乙方主张金额")
  private String partyBAmount;

  @ApiModelProperty("双方协商一致金额")
  private String bothAgreedAmount;

  @ApiModelProperty("争议情况概述")
  private String disputeDescription;

  @ApiModelProperty("其他")
  private String other;

  @ApiModelProperty("审批状态: approval审批中；finish审批完成")
  private String approvalStatus;

  @ApiModelProperty("是否已阅: read已阅； beread待阅")
  private String hasRead;

  @ApiModelProperty("观察状态: special_attention特别关注；warning 预警；normal正常")
  private String monitorStatus;
  /** */
  @ApiModelProperty("预警流程节点: untreated待办理； transfer 已转办；feedback 已反馈；finish 已办结")
  private String alertNode;

  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty("更新时间")
  private LocalDateTime updateTime;

  @ApiModelProperty("混合字段，用户存储动态扩展的信息")
  private String mixedField;

  @ApiModelProperty("办结结果")
  private String finishType;

  @ApiModelProperty("操作名称")
  private String operationName;

  @ApiModelProperty("被操作人id")
  private String byOperationPeopleId;
}
