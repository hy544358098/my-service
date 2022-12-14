package com.crc.common.enums.oa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpecialStatusEnum {

     STATUS_WARN("warning", "预警/转预警"),

     STATUS_TRANSFER("transfer", "已转办"),

     STATUS_INFORM("inform", "知会"),

     STATUS_FEEDBACK("feedback", "已反馈"),

     STATUS_FINISH("finish", "已办结"),

     STATUS_APPROVAL("approval","待审批"),

     STATUS_READ("read", "已阅"),

     STATUS_BEREAD("beread", "待阅"),

     STATUS_ATTENTION("special_attention", "特别关注"),

     STATUS_UNTREATED("untreated","待办理"),

     STATUS_NORMAL("normal", "正常");


     private String code;
     private String name;

     public String getCode(){
          return code;
     }

     public String getName(){
          return name;
     }
}
