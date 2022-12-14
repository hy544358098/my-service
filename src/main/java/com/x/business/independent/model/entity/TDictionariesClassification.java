package com.x.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoming
 * @date 2021-09-27 11:16:25
 */
@Data
@TableName("t_dictionaries_classification")
public class TDictionariesClassification implements Serializable {
  private static final long serialVersionUID = 1L;

  /** */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /** 分类名称 */
  private String classificationName;
  /** 分类代码 */
  private String classificationCode;
}
