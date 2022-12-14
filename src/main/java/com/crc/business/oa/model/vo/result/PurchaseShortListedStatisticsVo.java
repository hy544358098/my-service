package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 投标入围单位审批统计报表实体
 * @Author YANKAIBO
 * @Date 2021/11/29 16:56
 * @Version 1.0
 */
@Data
public class PurchaseShortListedStatisticsVo {

    @ApiModelProperty("城市公司")
    private String cityName;

    @ApiModelProperty("单位名称")
    private String companyName;

    @ApiModelProperty("投标入围次数")
    private Integer total = 0;

    @ApiModelProperty(value = "表单信息")
    private String tableInfo;

    @ApiModelProperty(value = "申请时间")
    private String applyDate;

}
