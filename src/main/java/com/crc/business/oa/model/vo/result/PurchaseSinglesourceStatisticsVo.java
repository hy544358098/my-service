package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/25 10:20
 * @Version 1.0
 */
@Data
public class PurchaseSinglesourceStatisticsVo {

    @ApiModelProperty("采购预估金额年")
    private String estimatedAmountYear;

    @ApiModelProperty("城市公司年")
    private String statisticsCityNameYear;

    @ApiModelProperty("采购预估金额日")
    private String estimatedAmountDay;

    @ApiModelProperty("城市公司日")
    private String statisticsCityNameDay;
}
