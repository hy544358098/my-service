package com.crc.business.independent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author xiaoming */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileModel {
  /** 上传链接关联表id */
  private Long id;
  /** 文件大小 */
  private long fileSize;

  /** 文件类型 */
  private String fileType;

  /** 文件名称 */
  private String fileName;
}
