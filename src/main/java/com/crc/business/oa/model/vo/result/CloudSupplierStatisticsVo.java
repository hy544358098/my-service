package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/29 14:40
 * @Version 1.0
 */
@Data
public class CloudSupplierStatisticsVo {

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("供方名称")
    private String supplyName;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("引进说明")
    private String introduction;

    @ApiModelProperty("动态表格内容")
    private String tableInfo;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;

    @ApiModelProperty("用于统计的城市公司")
    private String approvalStatus;

}
