package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/28 11:01
 * @Version 1.0
 */
@Data
public class MarketingApplyParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("归属组织")
    private String belongingOrg;

    @ApiModelProperty("预算执行类型")
    private String budgetExecutionType;

    @ApiModelProperty("紧急程度")
    private String emergencyLevel;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("预算编制月")
    private String budgetMonth;

    @ApiModelProperty("一级科目")
    private String km1;
}
