package com.x.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Oa转换实体类
 * @Author: yankaibo
 * @Date: 2021/10/12 9:11
 * @Version: 1.0
 */
@Data
public class LawCaseFilingApprovalData {
    @ApiModelProperty("uuId")
    public String uid;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

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

    @ApiModelProperty("混合字段")
    private String mixedField;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
