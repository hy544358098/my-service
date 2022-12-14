package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CloudSupplierSourceData
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 11:12
 * @Version: 1.0
 */
@Data
public class CloudSupplierSourceData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

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

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
