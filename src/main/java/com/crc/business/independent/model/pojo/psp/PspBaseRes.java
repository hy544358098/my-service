package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

import java.io.Serializable;

@Data
public class PspBaseRes<T> implements Serializable {
    private Integer status;
    private String message;
    private T data;
}
