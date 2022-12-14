package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 *
 * @author xiaoming
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
  INTERFACE_ERROR("interface_error", "接口调用异常"),

  SERVICE_ERROR("service_error", "业务异常"),

  PARAM_ERROR("param_error", "参数错误"),

  FILE_FORMAT_ERROR("file_format_error", "不支持的文件格式！"),

  PARAM_PAGE_ERROR("param_page_error", "分页参数错误"),

  MISS_USERID("miss_userId", "登录信息错误"),

  PARAM_NULL("param_null", "参数不能为空"),

  MISS_TOKEN("miss_token", "请求缺少头部token参数"),

  ILLEGALITY_ERROR("illegality_error", "非法链接"),

  DATA_NOT_EXIST("ZDJD_001", "非法操作，数据不存在"),

  //  DATA_NOT_EXIST("ZDJD_002",""),

  DATA_ERROR("date_error", "数据异常"),

  DATA_NOT_REPEAT("date_repeat", "同一业务同一类型数据不能重复"),

  LOGIN_USER_NOT_EXIT("user_not_exit", "用户无此系统访问权限"),

  LOGIN_USER_REPEAT("user_pepeat", "用户名重复"),

  LOGIN_USER_AUTH_FAILED("user_auth_failed", "用户鉴权失败"),

  NAME_REPEAT("name_repeat", "类型名称重复"),

  CODE_REPEAT("code_repeat", "类型代码重复"),

  DOWNLOAD_ERROR("download_error", "下载错误"),

  UPLOAD_ERROR("upload_error", "上传错误"),

  DOWNLOAD_PARAMETER("download_parameter", "下载参数错误"),

  FINISH_NOT_OPERATE("supervision_error_0001", "已办结，无法操作"),

  NOT_COMPETENCE("supervision_error_0002", "您没有操作权限！");

  private String retCode;

  private String retMsg;
}
