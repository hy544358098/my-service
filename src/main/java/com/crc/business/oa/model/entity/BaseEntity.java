package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: xiaoming
 * @Date: 2021/10/26 10:25
 * @Version: 1.0
 */
@Data
public class  BaseEntity <T extends Model<T>> extends Model<T> {

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("编号")
    private String uid;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("审批状态: 起草drafting；approval审批中；finish审批完成")
    private String approvalStatus;

    @ApiModelProperty("是否已阅  finish_read已阅 beread待阅")
    private String hasRead;

    @ApiModelProperty("观察状态  special_attention特别关注 warning预警 normal正常")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点  untreated待办理 transfer已转办 feedback已反馈")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("是否抽取的数据：0：否；1：是")
    private int isExtract;

    @ApiModelProperty("用于统计的城市公司")
    private String statisticsCityName;
}
