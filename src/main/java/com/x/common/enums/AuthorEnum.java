package com.x.common.enums;

import lombok.Getter;

/**
 * @author wangxy
 * @Classname AuthorEnum
 * @Date 2021/3/29 3:05 下午
 */
@Getter
public enum AuthorEnum {
    ENABLE("1","启用"),
    DISABLE("0","禁用")
    ;

    private String code;

    private String msg;

    AuthorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
