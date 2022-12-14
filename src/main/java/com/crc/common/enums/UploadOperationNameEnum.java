package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaoming
 */

@AllArgsConstructor
@Getter
public enum UploadOperationNameEnum {
  SUMBIT("sumbit", "提交"),
  FEEDBCK("feedback", "反馈");

  private String code;
  private String name;
}
