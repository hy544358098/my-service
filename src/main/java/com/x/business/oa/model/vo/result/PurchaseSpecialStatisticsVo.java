package com.x.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 特殊资源比选统计报表实体
 * @Author YANKAIBO
 * @Date 2021/11/29 19:16
 * @Version 1.0
 */
@Data
public class PurchaseSpecialStatisticsVo {
    @ApiModelProperty("城市公司")
    private String cityName;

    @ApiModelProperty("今年总数")
    private Integer yearTotal = 0;

    @ApiModelProperty("今天总数")
    private Integer todayTotal = 0;

}
