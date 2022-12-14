package com.crc.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/24 10:21
 * @Version 1.0
 */
@Data
public class SupervisionStatisticsVo {

    @ApiModelProperty("总数量")
    private Integer total;

    @ApiModelProperty("廉洁、作风问题数量")
    private Integer cleanContent;

    @ApiModelProperty("工作、协同问题数量")
    private Integer workContent;

    @ApiModelProperty("风险、舆情问题数量")
    private Integer riskContent;

    @ApiModelProperty("意见建议数量")
    private Integer advice;

    @ApiModelProperty("提交人部门")
    private String submitDepartment;

    @ApiModelProperty("状态")
    private String status;
}
