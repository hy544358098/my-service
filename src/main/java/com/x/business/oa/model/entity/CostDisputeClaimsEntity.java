package com.x.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 工程争议索赔表
 *
 * @author xiaoming
 * @since 2021-10-13
 */
@Data
@TableName("t_oa_cost_disputeclaims")
public class CostDisputeClaimsEntity extends BaseEntity<CostDisputeClaimsEntity>
    implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("单据编号")
  private String documentNumber;

  @ApiModelProperty("申请日期")
  private String applyDate;

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

}
