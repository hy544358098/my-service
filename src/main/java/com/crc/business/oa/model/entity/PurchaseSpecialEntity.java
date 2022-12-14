package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 特殊资源比选内容信息表
 *
 * @date 2021-09-19 18:54:22
 */
@Data
@TableName("t_oa_purchase_special")
public class PurchaseSpecialEntity extends BaseEntity<PurchaseSpecialEntity> {

     @ApiModelProperty("经办人")
     private String operatorName;

     @ApiModelProperty("申请日期")
     private String applyDate;

     @ApiModelProperty("所属大区/公司")
     private String originalRegion;

     @ApiModelProperty("所属部门/项目")
     private String originalDep;

     @ApiModelProperty("采购分类")
     private String purchasingType;

     @ApiModelProperty("预算金额")
     private String estimatedAmount;

     @ApiModelProperty("过程描述")
     private String processDescription;
}
