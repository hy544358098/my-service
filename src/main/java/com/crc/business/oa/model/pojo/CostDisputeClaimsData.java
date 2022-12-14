package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** @Description: Oa转换实体类
 * @Author: yankaibo
 * @Date: 2021/10/12 9:11
 * @Version: 1.0 */
@Data
public class CostDisputeClaimsData {
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

  @ApiModelProperty("混合字段")
  private String mixedField;

  @ApiModelProperty("用于统计的城市公司")
  private String statisticsCityName;

  @ApiModelProperty("其他")
  private String other;
}
