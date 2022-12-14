package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/1 10:30
 * @Version 1.0
 */
@Data
public class ProjectChangeStatisticsVo {

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;

    @ApiModelProperty("变更预估金额合计")
    private double total;

}
