package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/28 13:38
 * @Version 1.0
 */
@Data
public class MarketingApplyVo {
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

    @ApiModelProperty("本次预算申请总金额")
    private String budgetTotal;

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
