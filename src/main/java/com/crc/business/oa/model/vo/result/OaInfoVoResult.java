package com.crc.business.oa.model.vo.result;

import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.oa.model.entity.AppendixEntity;
import com.crc.business.oa.model.entity.ApprovalEntity;
import com.crc.business.oa.model.entity.OaOperateEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * OA流程详情页通用返回体VO
 *
 * @date 2021-09-21 17:10:04
 */
@Data
public class OaInfoVoResult<T>{
  /***
   * 主表单信息
   */
  @ApiModelProperty("主表单字段")
  private T tOaPurchaseEntity;
  /***
   * 动态表单字段集合
   */
  @ApiModelProperty("动态表单字段")
  private Map<String, Object> dynamicFormMap;

  /***
   * 动态表格集合
   */
  @ApiModelProperty("动态表格字段")
  private List<OaTableInfo> tableInfoList;

  /***
   * 附件集合
   */
  @ApiModelProperty("附件字段")
  private List<AppendixEntity> tOaAppendFile;
  /***
   * 反馈附件
   */
  @ApiModelProperty("反馈附件字段")
  private List<UploadLinkAddress> uploadLinkAddress;

  /***
   * 审批信息集合
   */
  @ApiModelProperty("审批信息字段")
  private List<ApprovalEntity> tOaApproval;

  /***
   * 预警处理信息集合
   */
  @ApiModelProperty("预警处理信息字段")
  private List<OaOperateEntity> specialOperate;
}
