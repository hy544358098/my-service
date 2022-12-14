package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 党组织生活
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-29 16:07:12
 */
@Data
@TableName("t_oa_organization_life")
public class OrganizationLifeEntity extends Model<OrganizationLifeEntity>{
  private static final long serialVersionUID = 1L;


  @ApiModelProperty("自增主键")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty("ldapId")
  private String ldapId;

  @ApiModelProperty("编号")
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

  @ApiModelProperty("审批状态: 起草drafting；approval审批中；finish审批完成")
  private String approvalStatus;

  @ApiModelProperty("是否已阅  finish_read已阅 beread待阅")
  private String hasRead;

  @ApiModelProperty("观察状态  special_attention特别关注 warning预警 normal正常")
  private String monitorStatus;

  @ApiModelProperty("预警流程节点  untreated待办理 transfer已转办 feedback已反馈")
  private String alertNode;

  @ApiModelProperty("更新时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private LocalDateTime updateTime;

  @ApiModelProperty("创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private LocalDateTime createTime;

  @ApiModelProperty("办结结果")
  private String finishType;

  @ApiModelProperty("用于统计的城市公司")
  private String statisticsCityName;

}
