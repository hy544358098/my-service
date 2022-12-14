package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: CloudSupplierVO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:55
 * @Version: 1.0
 */
@Data
public class CloudSupplierVO {
    @ApiModelProperty("编号")
    private String uid;

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("组织")
    private String deptName;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("联系方式")
    private String contactInform;

    @ApiModelProperty("座机号")
    private String phone;

    @ApiModelProperty("引进说明")
    private String introduction;

    @ApiModelProperty("审批状态: approval 待审批；finish 审批完成")
    private String approvalStatus;

    @ApiModelProperty("是否已阅: read 已阅 ；beread 待阅")
    private String hasRead;

    @ApiModelProperty("观察状态:special_attention 特别关注； warning 预警； normal 正常")
    private String monitorStatus;

    @ApiModelProperty("预警流程节点：untreated 待办理； transfer 已转办； feedback 已反馈；finish 已办结")
    private String alertNode;

    @ApiModelProperty("混合字段，用户存储动态扩展的信息")
    private String mixedField;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("操作人节点信息")
    private String operationNodeName;
}
