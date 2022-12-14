package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 观察表
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
@Data
@TableName("t_my_supervision")
public class Supervision implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	/**
	 * 单据编号
	 */
	private String documentsNumber;
	/**
	 * 状态（"approval"-待审批，"untreated"-待办理，"transfer"-已转办，"feedback"-已反馈，"finish"-已办结）
	 */
	private String status;
	/**
	 * 提交人id
	 */
	private String submitPeopleId;
	/**
	 * 提交人
	 */
	private String submitPeople;
	/**
	 * 提交人部门id
	 */
	private String submitDepartmentId;
	/**
	 * 提交人部门
	 */
	private String submitDepartment;
	/**
	 * 提交日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private String submitTime;
	/**
	 * 被联系人id
	 */
	private String contactedPeopleId;
	/**
	 * 被联系人
	 */
	private String contactedPeople;
	/**
	 * 被联系人部门
	 */
	private String contactedDepartment;
	/**
	 * 被联系人部门id
	 */
	private String contactedDepartmentId;
	/**
	 * 问题线索涉及领域（"procurement"-采购，"marketing"-营销，"customer"-客户关系，" cost"-成本，"contract"-合约，"legal_compliance"-法律合规）
	 */
	private String problemClueField;
	/**
	 * 问题类别（"clean"-廉洁纪律，"work"-工作纪律，"political"-政治纪律，"style"-作风纪律）
	 */
	private String problemType;
	/**
	 * 紧急程度("low"-低，"middle"-中，"high"-高)
	 */
	private String riskLever;
	/**
	 * 是否匿名（"yes"-匿名，"no"-不匿名）
	 */
	private String anonymousType;

	/**
	 * 办结结果（"normal"：正常办结，"business_rectification"：业务整改，"transfer_supervision"：转问题，"transfer_problem_clue"：转问题线索）
	 */
	private String finishType;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date updateTime;
	/**
	 * 观察类型（"personal"-个人，"department"-部门）
	 */
	private String supervisionType;

	/**
	 * 逻辑删除（"Y"-是，"N"-否）
	 */
	private String delType;

	/**
	 * 廉洁、作风问题_具体内容
	 */
	private String cleanContent;

	/**
	 * 工作、协同问题_具体内容
	 */
	private String workContent;

	/**
	 * 风险、舆情问题_具体内容
	 */
	private String riskContent;

	/**
	 * 意见建议
	 */
	private String advice;

	/**
	 * 是否代表本部门填报
	 */
	private String isDeptFillIn;
}
