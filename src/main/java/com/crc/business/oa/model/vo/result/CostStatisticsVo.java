package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/29 15:51
 * @Version 1.0
 */
@Data
public class CostStatisticsVo {

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;

    @ApiModelProperty("双方协商一致金额")
    private String bothAgreedAmount;
}
