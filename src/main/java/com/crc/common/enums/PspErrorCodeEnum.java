package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @Description PSP服务异常编码
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum PspErrorCodeEnum {
    RESULT_NULL("301","解析报文错误"),
    REQUEST_PARAMS_NULL("303","参数不正确");

    private String retCode;

    private String retMsg;
}
