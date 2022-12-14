package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/3 10:44
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum SupervisionCodeEnum {
    SUPERVISION_TYPE_PERSONAL("personal","个人观察"),
    SUPERVISION_TYPE_DEPARTMENT("department","部门观察"),
    YES("yes","是"),
    MYSUPERVISION("my_supervision", "我要观察");

    private String code;
    private String name;
}
