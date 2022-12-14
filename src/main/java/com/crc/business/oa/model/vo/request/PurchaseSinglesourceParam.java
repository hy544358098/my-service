package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PurchaseSinglesourceParam extends CommonParam {

  @ApiModelProperty("发起部门")
  private String initiateDep;

  @ApiModelProperty("所属采购条线")
  private String ownedPurchasLine;

  @ApiModelProperty("采购分类")
  private String purchasingType;

  @ApiModelProperty("采购模式")
  private String procurementModel;

  @ApiModelProperty("甲方单位")
  private String partyA;

  @ApiModelProperty("乙方单位")
  private String partyB;

  @ApiModelProperty("观察状态")
  private String monitorStatus;

  @ApiModelProperty("预警流程节点")
  private String alertNode;

  @ApiModelProperty("是否已阅")
  private String hasRead;

  @ApiModelProperty("状态opeStatus")
  private String opeStatus;

}
