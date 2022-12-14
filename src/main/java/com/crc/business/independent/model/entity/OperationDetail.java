package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
@Data
@TableName("t_operation_detail")
public class OperationDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  /** */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /** 观察表id */
  private Integer supervisionId;
  /** 操作名称（"approval"-审批，"transfer"-转办，"Inform"-知会，"feedback"-反馈，"finish"-办结） */
  private String operationName;
  /** 操作人id */
  private String operationPeopleId;
  /** 操作人 */
  private String operationPeople;
  /** 被操作人id */
  private String byOperationPeopleId;
  /** 被操作人 */
  private String byOperationPeople;
  /** 操作时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date operationTime;
  /** 备注 */
  private String remark;
}
