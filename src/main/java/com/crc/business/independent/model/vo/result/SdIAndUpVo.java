package com.crc.business.independent.model.vo.result;

import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/** @author xiaoming */
@Data
public class SdIAndUpVo {
  @ApiModelProperty("id")
  private Integer id;

  @ApiModelProperty("年份")
  private String year;

  @ApiModelProperty("处理状态")
  private String handleStatus;

  @ApiModelProperty("问题线索时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date problemClueTime;

  @ApiModelProperty("案件编号")
  private String caseNumber;

  @ApiModelProperty("案件名称")
  private String caseName;

  @ApiModelProperty("案件概要")
  private String caseContent;

  @ApiModelProperty("是否立案")
  private String caseEstablishType;

  @ApiModelProperty("初核是否属实")
  private String examineType;

  @ApiModelProperty("问题线索涉及领域")
  private String problemClueField;

  @ApiModelProperty("违纪违规类别")
  private String violationType;

  @ApiModelProperty("处理人")
  private String handlePeopleId;

  @ApiModelProperty("性别")
  private String sex;

  @ApiModelProperty("所在部门")
  private String department;

  @ApiModelProperty("时任职务职级")
  private String rank;

  @ApiModelProperty("处分类别")
  private String punishmentType;

  @ApiModelProperty("处分情况")
  private String punishmentDetails;

  @ApiModelProperty("处分时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date punishmentTime;

  @ApiModelProperty("影响期（截至）")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date validityTime;

  @ApiModelProperty("追缴违规所得")
  private String violationMoney;

  @ApiModelProperty("挽回经济损失")
  private String toSaveMoney;

  @ApiModelProperty("扣发奖金绩效")
  private String deductionMoney;

  @ApiModelProperty("降本金额")
  private String dropAmount;

  @ApiModelProperty("主动上缴")
  private String activeTurnIn;

  @ApiModelProperty("线索来源")
  private String clueSource;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("链接表路径集合")
  private List<Integer> fileId;

  @ApiModelProperty("链接表集合")
  private List<UploadLinkAddress> filePathList;
}
