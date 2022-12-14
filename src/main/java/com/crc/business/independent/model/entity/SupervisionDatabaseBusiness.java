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
 * @date 2021-09-23 13:35:59
 */
@Data
@TableName("t_supervision_database_business")
public class SupervisionDatabaseBusiness implements Serializable {
  private static final long serialVersionUID = 1L;

  /** */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /** 问题编号 */
  private String problemNumber;
  /** 年份 */
  private String year;
  /** 部门id */
  private String departmentId;
  /** 部门 */
  private String department;
  /**
   * 问题来源（"inspection_feedback":纪检反馈，"department_own_query":部门自查，"audit_feedback"：审计反馈，"other":其他）
   */
  private String problemSource;
  /** 问题时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date problemTime;
  /** 经办人 */
  private String handlerName;
  /** 是否移交纪检("yes":是，"no":否) */
  private String transferInspectionType;
  /** 问题描述 */
  private String problemDescribe;
  /** 办结时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date finishTime;
  /** 办结结果 */
  private String finishResult;
  /** 备注 */
  private String remark;
  /** 修改时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updateTime;
  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date creatTime;
}
