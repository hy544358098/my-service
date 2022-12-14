package com.crc.business.independent.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SettingParamConditionPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:17
 * @Version: 1.0
 */
@Data
public class SettingParamConditionPO {

    @ApiModelProperty("是否自信息")
    private Integer isInformation;

    @ApiModelProperty("子信息")
    private String information;

    @ApiModelProperty("前置字段")
    private String beforeField;

    @ApiModelProperty("前置字段运算符")
    private Integer beforeFieldOperation;

    @ApiModelProperty("前置字段值")
    private String beforeFieldValue;

    @ApiModelProperty("判定字段-是否自信息")
    private Integer isInformationDeter;

    @ApiModelProperty("判定字段-子信息")
    private String informationDeter;

    @ApiModelProperty("判定字段")
    private String determinationField;

    @ApiModelProperty("判定字段运算符")
    private Integer determinationFieldOperation;

    @ApiModelProperty("判定字段值")
    private String determinationFieldValue;

    @ApiModelProperty("连接符")
    private Integer unionOperation;
}
