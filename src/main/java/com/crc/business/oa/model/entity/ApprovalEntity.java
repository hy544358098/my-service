package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA审批流程表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Data
@TableName("t_oa_approval")
public class ApprovalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

	/**
	 * uid
	 */
	private String uid;

	/**
	 * 序号
	 */
	private String number;
	/**
	 * 审批人
	 */
	private String approver;
	/**
	 * 审批内容
	 */
	private String approvalContent;
	/**
	 * 审批节点
	 */
	private String approvalNode;
	/**
	 * 审批时间
	 */
	private String approvalTime;
	/**
	 * 所属部门
	 */
	private String area;
	/**
	 * 创建时间
	 */
	private Date createTime;
}
