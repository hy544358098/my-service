package com.crc.business.independent.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 观察数据库--业务模块搜索参数
 * @author xiaoming
 */
@Data
public class DatabaseBusinessParam {

  @ApiModelProperty("ldapId")
  private String ldapId;

  @ApiModelProperty("id")
  private Integer id;

  @ApiModelProperty("问题编号")
  private String problemNumber;

  @ApiModelProperty("年份")
  private String year;

  @ApiModelProperty("问题来源")
  private String problemSource;

  @ApiModelProperty("是否移交纪检")
  private String transferInspectionType;

  @ApiModelProperty("部门")
  private String department;

  @ApiModelProperty("起始时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String startTime;

  @ApiModelProperty("结束时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String endTime;

  @ApiModelProperty("页")
  private Integer pageNum;

  @ApiModelProperty("行")
  private Integer pageSize;
}
