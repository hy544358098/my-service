package com.crc.business.independent.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AnnouncementParamPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/18 16:05
 * @Version: 1.0
 */
@Data
public class AnnouncementParamPO {

    @ApiModelProperty("uuid")
    private String uid;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("重要/普通")
    private Integer type;

    @ApiModelProperty("是否置顶")
    private Integer topType;

}
