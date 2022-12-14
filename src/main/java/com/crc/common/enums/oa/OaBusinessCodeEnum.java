package com.crc.common.enums.oa;

import lombok.Getter;

/** @Description OA接口编码 @Author YANKAIBO @Date 2021/10/29 13:51 @Version 1.0 */
@Getter
public enum OaBusinessCodeEnum {
  OA_CODE_FAILED("201", "接收失败"),
  OA_CODE_MESSAGE_ERROR("301", "解析报文错误"),
  OA_CODE_TOKEN_ERROR("302", "无效调用凭证"),
  OA_CODE_PARAM_ERROR("303", "参数不正确"),
  OA_CODE_PROCESS_TYPE_ERROR("304", "无效的OA流程"),
  OA_CODE_SYSTEM_ERROR("500", "系统内部错误");

  private String code;
  private String msg;

  OaBusinessCodeEnum(String status, String msg) {
    this.code = status;
    this.msg = msg;
  }
}
