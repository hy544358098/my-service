package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SettingParameter
 * @Description: 参数设置条件实体
 * @Author: xiaoming
 * @Date: 2021/10/19 11:11
 * @Version: 1.0
 */
@Data
@TableName("t_setting_parameter_condition")
public class SettingParameterCondition extends BaseEntity<SettingParameterCondition>{
    @ApiModelProperty("uuid")
    private String uid;

    @ApiModelProperty("参数uid")
    private String paramId;

    @ApiModelProperty("前置字段-是否自信息")
    private Integer isInformation;

    @ApiModelProperty("前置字段-子信息")
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

    @ApiModelProperty("链接符")
    private Integer unionOperation;
}
