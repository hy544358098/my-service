package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DesignChangeVo {
  @ApiModelProperty("自增主键")
  private Integer id;

  @ApiModelProperty("简易UUID")
  private String uid;

  @ApiModelProperty("单据编号")
  private String documentNumber;

  @ApiModelProperty("城市公司")
  private String cityFirm;

  @ApiModelProperty("申请日期")
  private String applyDate;

  @ApiModelProperty("标题")
  private String title;

  @ApiModelProperty("紧急程度")
  private String emergencyLevel;

  @ApiModelProperty("大区")
  private String region;

  @ApiModelProperty("项目")
  private String project;

  @ApiModelProperty("项目分期")
  private String projectStaging;

  @ApiModelProperty("申请部门")
  private String applyDepartment;

  @ApiModelProperty("经办人")
  private String handlerName;

  @ApiModelProperty("变更原因")
  private String reasonForChange;

  @ApiModelProperty("设计修改通知单编号")
  private String designModificationNoticeNumber;

  @ApiModelProperty("项目变更申请编号")
  private String projectChangeApplicationNumber;

  @ApiModelProperty("提出人")
  private String proposeName;

  @ApiModelProperty("是否考核")
  private String whetherAssess;

  @ApiModelProperty("变更类别")
  private String changeCategory;

  @ApiModelProperty("原因说明")
  private String reasonExplanation;

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

  @ApiModelProperty("问题线索涉及领域")
  private String problemClueField;

  @ApiModelProperty("办结结果")
  private String finishType;

  @ApiModelProperty("操作名称")
  private String operationName;

  @ApiModelProperty("被操作人id")
  private String byOperationPeopleId;

  @ApiModelProperty("此变更对预算的影响")
  private String impactOfThisChangeBudget;

  @ApiModelProperty("成本增减说明")
  private String costIncreaseDecreaseDescription;

  @ApiModelProperty("变更预估金额合计")
  private String changeTotalEstimatedAmount;
}
