package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * oa操作表
 *
 * @date 2021-09-20 11:45:02
 */
@Data
@TableName("t_oa_operation")
public class OaOperateEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("自增主键")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty("单一来源表uid")
  private String uid;

  @ApiModelProperty(
      "操作名称（\"warning\"-转预警,\"approval\"-审批，\"transfer\"-转办，\"Inform\"-知会，\"feedback\"-反馈，\"finish\"-办结） ")
  private String operationName;

  @ApiModelProperty("操作人id")
  private String operationPeopleId;

  @ApiModelProperty("操作人")
  private String operationPeople;

  @ApiModelProperty("被操作人id")
  private String byOperationPeopleId;

  @ApiModelProperty("被操作人")
  private String byOperationPeople;

  @ApiModelProperty("操作时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date operationTime;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("重点观察类型")
  private String processType;
}
