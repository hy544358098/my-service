package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/30 14:40
 * @Version 1.0
 */
@Data
public class LawCaseStatisticsVo {
    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;

    @ApiModelProperty("数量")
    private Integer total;
}
