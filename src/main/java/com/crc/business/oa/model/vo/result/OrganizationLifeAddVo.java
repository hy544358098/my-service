package com.crc.business.oa.model.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/1 14:47
 * @Version 1.0
 */
@Data
public class OrganizationLifeAddVo {

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("组织名称")
    private String associationName;

    @ApiModelProperty("活动、会议、党课名称")
    private String commonName;

    @ApiModelProperty("活动类型")
    private Integer activityType;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @ApiModelProperty("活动描述、会议内容、开课内容")
    private String content;

    @ApiModelProperty("会议地点")
    private String address;

    @ApiModelProperty("主持人")
    private String host;

    @ApiModelProperty("记录人")
    private String recorder;

    @ApiModelProperty("应到人数")
    private Integer shouldNumber;

    @ApiModelProperty("实到人数")
    private Integer actualNumber;

    @ApiModelProperty("会议议题、授课题目")
    private String topic;

    @ApiModelProperty("会议决议")
    private String resolution;

    @ApiModelProperty("学时")
    private Integer classHours;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("是否有党小组")
    private Integer isPartyGroup;

    @ApiModelProperty("党小组")
    private String partyGroup;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;

    @ApiModelProperty("链接表路径集合")
    private List<Integer> fileId;
}
