package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: PurchaseShortlistedEntity
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/11 14:05
 * @Version: 1.0
 */
@Data
@TableName("t_oa_purchase_cloud_supplier")
public class CloudSupplierEntity extends BaseEntity<CloudSupplierEntity> {

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("组织")
    private String deptName;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("联系方式")
    private String contactInform;

    @ApiModelProperty("座机号")
    private String phone;

    @ApiModelProperty("引进说明")
    private String introduction;

    @ApiModelProperty("单据备注")
    private String remark;
}