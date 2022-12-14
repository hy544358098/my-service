package com.crc.business.independent.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SettingParamPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:15
 * @Version: 1.0
 */
@Data
public class SettingParamPO {
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

    @ApiModelProperty("判定条件")
    private List<SettingParamConditionPO> list;
}
