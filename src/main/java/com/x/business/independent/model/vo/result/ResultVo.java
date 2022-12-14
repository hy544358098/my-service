package com.x.business.independent.model.vo.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 9203891633900322213L;


    /**
     * 信息码
     */
    private String code;

    /**
     * 请求内容
     */
    private String msg;

    /**
     * 请求内容
     */
    private T data;
}
