package com.x.business.oa.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/10/29 16:34
 * @Version 1.0
 */
@Data
public class OrganizationLifeParam extends CommonParam {


    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("组织名称")
    private String associationName;

    @ApiModelProperty("活动、会议、党课名称")
    private String commonName;

    @ApiModelProperty("活动类型")
    private Integer activityType;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String endTime;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("会议议题、授课题目")
    private String topic;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("状态opeStatus")
    private String opeStatus;

}
