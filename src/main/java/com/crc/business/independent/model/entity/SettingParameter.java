package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SettingParameter
 * @Description: 参数设置实体
 * @Author: xiaoming
 * @Date: 2021/10/19 11:11
 * @Version: 1.0
 */
@Data
@TableName("t_setting_parameter")
public class SettingParameter extends BaseEntity<SettingParameter>{
    @ApiModelProperty("uuid")
    private String uid;

    @ApiModelProperty("业务流程字典id")
    private Integer dictionaryId;

    @ApiModelProperty("条件类型")
    private String type;

    @ApiModelProperty("提醒时间")
    private Integer reminderTime;

    @ApiModelProperty("处理时间")
    private Integer expireTime;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("抽取原则")
    private Integer extractAmount;
}
