package com.x.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 营销年度预算列表实体
 * @Author: xiaoming
 * @Date: 2021/10/12 14:55
 * @Version: 1.0
 */
@Data
public class MarketingAnnualBudgetVO {
    @ApiModelProperty("编号")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("预算名称")
    private String budgetName;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("申请人")
    private String applyName;

    @ApiModelProperty("所属大区")
    private String region;

    @ApiModelProperty("预算归口部门")
    private String budgetDept;

    @ApiModelProperty("预算年度")
    private String budgetAmount;

    @ApiModelProperty("项目投批费用")
    private String approvalFee;

    @ApiModelProperty("项目投批签约额")
    private String approvalContractAmount;

    @ApiModelProperty("项目投批费用率")
    private String approvalFeeRate;

    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("审批状态: approval 待审批；finish 审批完成")
    private String approvalStatus;

    @ApiModelProperty("是否已阅: read 已阅 ；beread 待阅")
    private String hasRead;

    @ApiModelProperty("观察状态:special_attention 特别关注； warning 预警； normal 正常")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点：untreated 待办理； transfer 已转办； feedback 已反馈；finish 已办结")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("操作人节点信息")
    private String operationNodeName;
}
