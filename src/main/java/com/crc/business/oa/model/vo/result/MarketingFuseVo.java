package com.crc.business.oa.model.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/3 15:08
 * @Version 1.0
 */
@Data
public class MarketingFuseVo {
    @ApiModelProperty("自增主键")
    private Integer id;

    @ApiModelProperty("简易UUID")
    private String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
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

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;
}
