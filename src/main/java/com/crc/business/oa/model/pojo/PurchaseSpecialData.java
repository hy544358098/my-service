package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/25 14:27
 * @Version 1.0
 */
@Data
public class PurchaseSpecialData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("城市公司")
    private String cityFirm;

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

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
