package com.crc.business.oa.model.vo.result;

import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.oa.model.entity.AppendixEntity;
import com.crc.business.oa.model.entity.ApprovalEntity;
import com.crc.business.oa.model.entity.PurchaseSpecialEntity;
import com.crc.business.oa.model.entity.OaOperateEntity;
import lombok.Data;

import java.util.List;

@Data
public class OaDetailVo {
  // 基本信息
  private PurchaseSpecialEntity TOaSpecialEntity;
  // OA附件
  private List<AppendixEntity> tOaAppendixList;
  // 反馈附件
  private List<UploadLinkAddress> uploadLinkAddress;
  // 审批信息
  private List<ApprovalEntity> specialApprove;
  // 预警处理信息
  private List<OaOperateEntity> specialOperate;
}
