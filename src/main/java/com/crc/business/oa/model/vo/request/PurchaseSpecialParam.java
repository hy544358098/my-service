package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PurchaseSpecialParam extends CommonParam{

  @ApiModelProperty("单据编号")
  private String documentNumber;

  @ApiModelProperty("所属大区/公司")
  private String cityFirm;

  @ApiModelProperty("所属部门/项目")
  private String originalDep;

  @ApiModelProperty("采购分类")
  private String purchasingType;

  @ApiModelProperty("观察状态")
  private String monitorStatus;

  @ApiModelProperty("预警流程节点")
  private String alertNode;

  @ApiModelProperty("是否已阅")
  private String hasRead;
}
