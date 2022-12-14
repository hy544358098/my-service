package com.crc.business.oa.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author YANKAIBO
 */
@Data
public class ApprovalComment {
    /**
     * 序号
     */
    String number;

    /**
     * 单据编号
     */
    @JsonProperty("documentNumber")
    String uid;

    /**
     * 审批人
     */
    String approver;

    /**
     * 审批内容
     */
    String approvalContent;

    /**
     * 审批节点
     */
    String approvalNode;

    /**
     * 审批时间
     */
    String approvalTime;

    /**
     * 所属部门
     */
    String area;
}
