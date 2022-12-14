package com.crc.business.independent.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我要观察模块搜索参数
 *
 * @author xiaoming
 */
@Data
public class SupervisionParam {

  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("ldapId")
  private String ldapId;

  @ApiModelProperty("角色名称")
  private String roleName;

  @ApiModelProperty("部门角色")
  private String roleNameDepartment;

  @ApiModelProperty("提交人部门id")
  private String submitDepartmentId;

  @ApiModelProperty("单据编号")
  private String documentsNumber;

  @ApiModelProperty("问题线索涉及领域")
  private String problemClueField;

  @ApiModelProperty("问题类别")
  private String problemType;

  @ApiModelProperty("风险等级")
  private String riskLever;

  @ApiModelProperty("状态")
  private String status;

  @ApiModelProperty("起始时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String startTime;

  @ApiModelProperty("结束时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String endTime;

  @ApiModelProperty("观察类型")
  private String supervisionType;

  @ApiModelProperty("逻辑删除")
  private String delType;

  @ApiModelProperty("页")
  private Integer pageNum;

  @ApiModelProperty("行")
  private Integer pageSize;

}
