package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoming
 * @date 2021-09-18 11:31:02
 */
@Data
@TableName("t_supervision_database_inspection")
public class SupervisionDatabaseInspection implements Serializable {
  private static final long serialVersionUID = 1L;

  /** */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /** 年份 */
  private String year;
  /** 处理状态（"not_start":未启动，"start":启动，"Finish"：办结） */
  private String handleStatus;
  /** 问题线索时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date problemClueTime;
  /** 案件编号 */
  private String caseNumber;
  /** 案件名称 */
  private String caseName;
  /** 案件概要 */
  private String caseContent;
  /** 是否立案("yes":立案，"no":不立案) */
  private String caseEstablishType;
  /** 初核是否属实("yes":属实，"no":不属实) */
  private String examineType;
  /**
   * 问题线索涉及领域（"procurement"-采购，"marketing"-营销，"customer"-客户关系，"
   * cost"-成本，"contract"-合约，"legal_compliance"-法律合规）
   */
  private String problemClueField;
  /**
   * 违纪违规类别（"clean"-廉洁纪律，"work"-工作纪律，"political"-政治纪律，"organization"-作风纪律，"social_morality"-违反社会公德行为)
   */
  private String violationType;
  /** 处理人 */
  private String handlePeopleId;
  /** 性别（"man":男人，"woman":女人） */
  private String sex;
  /** 所在部门 */
  private String department;
  /** 职级 */
  private String rank;
  /** 处分类别（"party":党纪处罚，"politics":政纪处罚，"organization":组织处罚，"other_punishment":其他处罚） */
  private String punishmentType;
  /** 处分情况 */
  private String punishmentDetails;
  /** 处分时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date punishmentTime;
  /** 影响期至 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date validityTime;
  /** 追缴违规所得(元) */
  private String violationMoney;
  /** 挽回经济损失(元）扣发奖金绩效（单位：元），主动上缴（单位：元） */
  private String toSaveMoney;
  /** 扣发奖金绩效(元) */
  private String deductionMoney;
  /** 主动上缴(元) */
  private String activeTurnIn;
  /** 降本金额(元) */
  private String dropAmount;
  /** 修改时间 */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updateTime;
  /**
   * 线索来源（"my_supervision":我要观察，"important_supervision":重点观察，"eail":邮件，"leader_assign":领导交办，"supplier_report"：供应商举报，"customer_report":客户举报，"staff_report":员工举报，"competitor_report":同行业竞争对手举报，"other":其他）
   */
  private String clueSource;
  /** 备注 */
  private String remark;
}
