package com.crc.business.independent.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/22 14:51
 * @Version 1.0
 */
@Data
public class SupervisionExcel {

    @ApiModelProperty("单据编号")
    private String documentsNumber;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("提交人")
    private String submitPeople;

    @ApiModelProperty("提交人部门")
    private String submitDepartment;

    @ApiModelProperty("提交日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date submitTime;

    @ApiModelProperty("被联系人")
    private String contactedPeople;

    @ApiModelProperty("被联系人部门")
    private String contactedDepartment;

    @ApiModelProperty("廉洁、作风问题_具体内容")
    private String cleanContent;

    @ApiModelProperty("工作、协同问题_具体内容")
    private String workContent;

    @ApiModelProperty("风险、舆情问题_具体内容")
    private String riskContent;

    @ApiModelProperty("意见建议")
    private String advice;

    @ApiModelProperty("问题线索涉及领域")
    private String problemClueField;

    @ApiModelProperty("紧急程度")
    private String riskLever;

    @ApiModelProperty("是否匿名")
    private String anonymousType;

}
