package com.crc.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/3 18:01
 * @Version: 1.0
 */
@Data
public class OwnerDataCount {
    @ApiModelProperty("累计观察")
    private Integer allSupervision;
    @ApiModelProperty("今日观察")
    private Integer daySupervision;
    @ApiModelProperty("累计抽检")
    private Integer samplingSupervision;
    @ApiModelProperty("我要观察")
    private Integer allMySupervision;
}
