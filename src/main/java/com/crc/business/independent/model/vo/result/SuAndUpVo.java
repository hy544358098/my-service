package com.crc.business.independent.model.vo.result;

import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/** @author xiaoming */
@Data
public class SuAndUpVo {

  @ApiModelProperty("登陆id")
  private Integer id;

  @ApiModelProperty("单据编号")
  private String documentsNumber;

  @ApiModelProperty("状态")
  private String status;

  @ApiModelProperty("提交人id")
  private String submitPeopleId;

  @ApiModelProperty("提交人")
  private String submitPeople;

  @ApiModelProperty("提交人部门id")
  private String submitDepartmentId;

  @ApiModelProperty("提交人部门")
  private String submitDepartment;

  @ApiModelProperty("提交日期")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date submitTime;

  @ApiModelProperty("提交日期--string格式")
  private String submitTimeString;

  @ApiModelProperty("被联系人id")
  private String contactedPeopleId;

  @ApiModelProperty("被联系人")
  private String contactedPeople;

  @ApiModelProperty("被联系人部门id")
  private String contactedDepartmentId;

  @ApiModelProperty("被联系人部门")
  private String contactedDepartment;

  @ApiModelProperty("廉洁、作风问题_具体内容")
  private String cleanContent;

  @ApiModelProperty("工作、协同问题_具体内容")
  private String workContent;

  @ApiModelProperty("风险、舆情问题_具体内容")
  private String riskContent;

  @ApiModelProperty("意见建议")
  private String advice;

  @ApiModelProperty("问题线索涉及领域")
  private String problemClueField;

  @ApiModelProperty("问题类别")
  private String problemType;

  @ApiModelProperty("紧急程度")
  private String riskLever;

  @ApiModelProperty("是否匿名")
  private String anonymousType;

  @ApiModelProperty("需回复时间string格式")
  private String replyTimeString;

  @ApiModelProperty("修改时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updateTime;

  @ApiModelProperty("观察类型")
  private String supervisionType;

  @ApiModelProperty("操作名称")
  private String operationName;

  @ApiModelProperty("办结结果")
  private String finishType;

  @ApiModelProperty("是否代表本部门填报")
  private String isDeptFillIn;

  @ApiModelProperty("逻辑删除")
  private String delType;

  @ApiModelProperty("链接表路径集合")
  private List<Integer> fileId;

  @ApiModelProperty("链接表集合")
  private List<UploadLinkAddress> filePathList;
}
