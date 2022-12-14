package com.crc.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/23 14:52
 * @Version 1.0
 */
@Data
public class DatabaseStatisticsVo {

     @ApiModelProperty("年份")
     private String year;

     @ApiModelProperty("数量")
     private Integer total;

     @ApiModelProperty("问题线索涉及领域")
     private String problemClueField;

     @ApiModelProperty("处罚类别")
     private String punishmentType;

     @ApiModelProperty("追缴违规所得")
     private double violationMoney;

     @ApiModelProperty("挽回经济损失")
     private double toSaveMoney;

     @ApiModelProperty("扣发奖金绩效")
     private double deductionMoney;

     @ApiModelProperty("主动上缴")
     private double activeTurnIn;

     @ApiModelProperty("降本金额")
     private double dropAmount;

     @ApiModelProperty("追赃扣罚总金额")
     private double ChaseDirtyMoney;

     @ApiModelProperty("挽损降本总金额")
     private double RedeemLossMoney;
}

