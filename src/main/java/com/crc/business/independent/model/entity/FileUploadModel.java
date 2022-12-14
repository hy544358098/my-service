package com.crc.business.independent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author BEYONDSOFT-101 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadModel extends FileModel {
  /** 云存储中的路径 */
  private String filePath;
}
