package com.x.business.independent.model.vo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestVo implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;
    
    private int pageNum = 1;
    private int pageSize = 10;

    private String sortField;
    private String sortOrder;
}
