package com.crc.business.oa.model.vo.result;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 法律合规-案件处理方案审批
 * @Author: yankaibo
 * @Date: 2021/10/11 14:05
 * @Version: 1.0
 */
@Data
public class LawCaseFilingApprovalVO extends Model<LawCaseFilingApprovalVO> {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("简易的UUID，确保唯一，关联其它子表")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("申请日期")
    private String applyDate;

    @ApiModelProperty("申请人")
    private String applyName;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("案件名称")
    private String caseName;

    @ApiModelProperty("纠纷解决方式")
    private String disputeResolution ;

    @ApiModelProperty("最高院案由")
    private String supremeCourtCause;

    @ApiModelProperty("发案原因")
    private String caseReason;

    @ApiModelProperty("涉案标的金额(万元人民币)")
    private String amountInvolved;

    @ApiModelProperty("其他发案原因")
    private String otherCaseReason;

    @ApiModelProperty("重大程度")
    private String majorDegree;

    @ApiModelProperty("主办单位")
    private String organizer;

    @ApiModelProperty("诉讼目标")
    private String litigationGoal;

    @ApiModelProperty("诉讼目标偏差率")
    private String litigationGoalDeviationRate;

    @ApiModelProperty("诉讼/仲裁理由或答辩要点")
    private String reasonsForArbitration;

    @ApiModelProperty("案件争议焦点及利弊分析")
    private String focusOfCase;

    @ApiModelProperty("请示说明")
    private String pleaseIndicate;

    @ApiModelProperty("审批状态: approval审批中；finish审批完成")
    private String approvalStatus;

    @ApiModelProperty("是否已阅: read已阅； beread待阅")
    private String hasRead;

    @ApiModelProperty("观察状态: special_attention特别关注；warning 预警；normal正常 ")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点: untreated待办理； transfer 已转办；feedback 已反馈；finish 已办结")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;
}