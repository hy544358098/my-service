package com.crc.business.oa.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/3 15:14
 * @Version 1.0
 */
@Data
public class MarketingFuseData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date applyDate;

    @ApiModelProperty("申请人")
    private String applyName;

    @ApiModelProperty("预算责任部门")
    private String budgetDutyDepartment;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("归属组织")
    private String attributionOrganization;

    @ApiModelProperty("预算版本")
    private String budgetVersion;

    @ApiModelProperty("预算年度")
    private String budgetYear;

    @ApiModelProperty("项目全周期目标签约")
    private String projectCycleTargetSignUp;

    @ApiModelProperty("项目全周期目标费用")
    private String projectCycleTargetCost;

    @ApiModelProperty("项目全周期目标费率")
    private String projectCycleTargetRate;

    @ApiModelProperty("累计已发生费用")
    private String totalCost;

    @ApiModelProperty("季度节点说明")
    private String quarterlyIllustrate;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
