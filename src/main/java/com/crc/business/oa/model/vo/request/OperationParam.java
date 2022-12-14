package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: ShortListPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:27
 * @Version: 1.0
 */
@Data
public class OperationParam {
    @ApiModelProperty("关联表uid")
    private String supervisionId;

    @ApiModelProperty("操作名称（approval-审批，transfer-转办，Inform-知会，" +
            "feedback-反馈，finish-办结）")
    private String operationName;

    @ApiModelProperty("操作人id")
    private String operationPeopleId;

    @ApiModelProperty("操作人名称")
    private String operationPeople;

    @ApiModelProperty("被操作人id(批量)")
    private List<String> byOperationPeopleIdList=new ArrayList<>();

    @ApiModelProperty("操作时间")
    private LocalDateTime operationTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("问题线索及领域")
    private String problemClueField;

    @ApiModelProperty("办结结果（normal：正常办结，business_rectification： "+
            "业务整改，transfer_supervision：转问题，transfer_problem_clue：转问题线索）")
    private String finishType;

    @ApiModelProperty("链接表路径集合")
    private List<Integer> fileId;


    @ApiModelProperty("重点观察类型")
    private String processType;
}
