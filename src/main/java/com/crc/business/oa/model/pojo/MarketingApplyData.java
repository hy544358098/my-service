package com.crc.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/28 11:11
 * @Version 1.0
 */
@Data
public class MarketingApplyData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("申请人")
    private String applicant;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("归属组织")
    private String belongingOrg;

    @ApiModelProperty("预算归口部门")
    private String budgetFocalPoint;

    @ApiModelProperty("预算执行类型")
    private String budgetExecutionType;

    @ApiModelProperty("预算编制月")
    private String budgetMonth;

    @ApiModelProperty("本次预算申请总金额（元）")
    private String budgetTotal;

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
