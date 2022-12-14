package com.crc.business.independent.model.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SupervisionVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("单据编号")
    private String documentsNumber;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("提交人")
    private String submitPeople;

    @ApiModelProperty("提交人id")
    private String submitPeopleId;

    @ApiModelProperty("提交人部门")
    private String submitDepartment;

    @ApiModelProperty("提交日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date submitTime;

    @ApiModelProperty("提交日期string格式")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String submitTimeString;

    @ApiModelProperty("被联系人")
    private String contactedPeople;

    @ApiModelProperty("被联系人部门")
    private String contactedDepartment;

    @ApiModelProperty("意见建议")
    private String advice;

    @ApiModelProperty("问题线索涉及领域")
    private String problemClueField;

    @ApiModelProperty("风险等级")
    private String riskLever;

    @ApiModelProperty("是否匿名")
    private String anonymousType;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;

    @ApiModelProperty("逻辑删除")
    private String delType;

    @ApiModelProperty("廉洁、作风问题_具体内容")
    private String cleanContent;

    @ApiModelProperty("工作、协同问题_具体内容")
    private String workContent;

    @ApiModelProperty("风险、舆情问题_具体内容")
    private String riskContent;

    @ApiModelProperty("是否代表本部门填报")
    private String isDeptFillIn;

    @ApiModelProperty("是否置顶")
    private String topType;

    @ApiModelProperty("spare")
    private String spare;
}
