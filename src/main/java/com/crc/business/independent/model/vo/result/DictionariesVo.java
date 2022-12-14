package com.crc.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BEYONDSOFT-101
 */
@Data
public class DictionariesVo {

    @ApiModelProperty("字典分类表id")
    private Integer classificationId;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("类型代码")
    private String typeCode;

    @ApiModelProperty("页")
    private Integer pageNum;

    @ApiModelProperty("行")
    private Integer pageSize;
}
