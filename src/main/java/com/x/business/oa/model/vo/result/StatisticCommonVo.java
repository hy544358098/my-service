package com.x.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author YANKAIBO
 * @Date 2021/11/29 13:52
 * @Version 1.0
 */
@Data
public class StatisticCommonVo {
    @ApiModelProperty("城市公司")
    private String cityName;
    @ApiModelProperty("总量")
    private double total = 0.0;
}
