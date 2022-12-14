package com.crc.business.independent.model.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: SettingParamSearchPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:46
 * @Version: 1.0
 */
@Data
public class SettingParameterListVo {
    @ApiModelProperty("uuid")
    private String uid;
    @ApiModelProperty("业务流程字典")
    private String dictionaryName;
    @ApiModelProperty("条件类型")
    private String typeName;
    @ApiModelProperty("类型编码")
    private String typeCode;
    @ApiModelProperty("描述")
    private String remark;
    @ApiModelProperty("抽取比例")
    private Integer extractAmount;
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
