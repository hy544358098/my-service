package com.x.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoming
 * @date 2021-09-15 17:47:57
 */
@Data
@TableName("t_upload_link_address")
public class UploadLinkAddress implements Serializable {
  private static final long serialVersionUID = 1L;

  /** */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  /** 观察表id */
  private String uid;
  /** 文件名称 */
  private String fileName;
  /** 文件路径 */
  private String filePath;
  /** 上传人id */
  private String uploadPeopleId;
  /** 创建时间 */
  private Date creatTime;
  /** 操作名称（"sumbit"-上传，"feedback"-反馈） */
  private String operationName;
  /** 业务属性("supervision":观察，"supervision_database":观察数据库) */
  private String businessType;
  /** 文件类型（1.附件 2.图片） */
  private Integer fileType;
}
