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
 * @date 2021-09-27 11:16:25
 */
@Data
@TableName("t_dictionaries_detailed")
public class TDictionariesDetailed implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 自增id */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /** 字典分类表id */
  private Integer classificationId;
  /** 类型名称 */
  private String typeName;
  /** 类型代码 */
  private String typeCode;
  /** 描述 */
  private String remarks;
  /** 分类名称 */
  private String classificationName;
  /** 分类代码 */
  private String classificationCode;
  /** 创建人姓名 */
  private String creatId;
  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date creatTime;
  /** 修改人姓名 */
  private String updateId;
  /** 修改时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;
}
