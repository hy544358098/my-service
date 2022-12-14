package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 营销进度熔断重启
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-03 14:40:08
 */
@Data
@TableName("t_oa_marketing_fuse")
public class MarketingFuseEntity extends BaseEntity<MarketingFuseEntity> {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("标题")
  private String title;

  @ApiModelProperty("申请日期")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date applyDate;

  @ApiModelProperty("申请人")
  private String applyName;

  @ApiModelProperty("预算责任部门")
  private String budgetDutyDepartment;

  @ApiModelProperty("项目名称")
  private String projectName;

  @ApiModelProperty("归属组织")
  private String attributionOrganization;

  @ApiModelProperty("预算版本")
  private String budgetVersion;

  @ApiModelProperty("预算年度")
  private String budgetYear;

  @ApiModelProperty("项目全周期目标签约")
  private String projectCycleTargetSignUp;

  @ApiModelProperty("项目全周期目标费用")
  private String projectCycleTargetCost;

  @ApiModelProperty("项目全周期目标费率")
  private String projectCycleTargetRate;

  @ApiModelProperty("累计已发生费用")
  private String totalCost;

  @ApiModelProperty("季度节点说明")
  private String quarterlyIllustrate;

}
