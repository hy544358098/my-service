package com.crc.business.independent.model.vo.result;

import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/** @author xiaoming */
@Data
public class SdbAndUpVo {
  @ApiModelProperty("id")
  private Integer id;

  @ApiModelProperty("问题编号")
  private String problemNumber;

  @ApiModelProperty("年份")
  private String year;

  @ApiModelProperty("部门id")
  private String departmentId;

  @ApiModelProperty("部门")
  private String department;

  @ApiModelProperty("问题来源")
  private String problemSource;

  @ApiModelProperty("问题时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date problemTime;

  @ApiModelProperty("经办人")
  private String handlerName;

  @ApiModelProperty("是否移交纪检")
  private String transferInspectionType;

  @ApiModelProperty("问题描述")
  private String problemDescribe;

  @ApiModelProperty("办结时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date finishTime;

  @ApiModelProperty("办结结果")
  private String finishResult;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("链接表路径集合")
  private List<Integer> fileId;

  @ApiModelProperty("链接表集合")
  private List<UploadLinkAddress> filePathList;
}
