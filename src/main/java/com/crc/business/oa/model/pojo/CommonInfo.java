package com.crc.business.oa.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CommonInfo {

    /**
     * 附件信息集合
     */
    List<AppendixInfo> appendixList;

    /**
     * 审批信息集合
     */
    List<ApprovalComment> approvalComments;

    /**
     * 动态表格集合
     */
    List<TableContent> tableList;
}
