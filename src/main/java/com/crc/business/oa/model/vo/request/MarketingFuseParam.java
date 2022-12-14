package com.crc.business.oa.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/3 14:52
 * @Version 1.0
 */
@Data
public class MarketingFuseParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("归属组织")
    private String attributionOrganization;

    @ApiModelProperty("预算责任部门")
    private String budgetDutyDepartment;

    @ApiModelProperty("起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endTime;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("状态opeStatus")
    private String opeStatus;

}
