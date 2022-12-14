package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 营销预算执行申请
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-28 10:25:39
 */
@Data
@TableName("t_oa_marketing_apply")
public class MarketingApplyEntity extends BaseEntity<MarketingApplyEntity> {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("申请日期")
	private String applyDate;

	@ApiModelProperty("部门")
	private String department;

	@ApiModelProperty("申请人")
	private String applicant;

	@ApiModelProperty("项目名称")
	private String projectName;

	@ApiModelProperty("归属组织")
	private String belongingOrg;

	@ApiModelProperty("预算归口部门")
	private String budgetFocalPoint;

	@ApiModelProperty("预算执行类型")
	private String budgetExecutionType;

	@ApiModelProperty("预算编制月")
	private String budgetMonth;

	@ApiModelProperty("本次预算申请总金额")
	private String budgetTotal;

}
