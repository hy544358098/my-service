package com.crc.business.oa.model.pojo;

import lombok.Data;

@Data
public class TableContent {

    /**
     * 表格名称
     */
    String tableName;

    /**
     * 表格信息(一个表格的数据集合字符串形式)
     */
    String tableInfo;
}
