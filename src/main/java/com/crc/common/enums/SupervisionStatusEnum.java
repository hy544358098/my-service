package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaoming
 */

@AllArgsConstructor
@Getter
public enum SupervisionStatusEnum {
  STATUS_REJECT("reject", "驳回"),
  STATUS_ADOPT("adopt", "通过"),
  STATUS_PENDING("approval", "待审批"),
  STATUS_UNTREATED("untreated", "待办理"),
  STATUS_TRANSFER("transfer", "已转办"),
  STATUS_INFORM("inform", "知会"),
  STATUS_FEEDBACK("feedback", "已反馈"),
  STATUS_FINISH("finish", "已办结");

  private String code;
  private String name;
}
