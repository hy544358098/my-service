package com.crc.business.independent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/** @author xiaoming */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadModel extends FileModel {

  /** 文件流 */
  private InputStream stream;
}
