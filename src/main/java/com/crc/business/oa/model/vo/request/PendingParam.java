package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/6 9:37
 * @Version 1.0
 */
@Data
public class PendingParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("业务流程")
    private String tableName;

    @ApiModelProperty("状态")
    private String alertNode;

    @ApiModelProperty("申请人")
    private String operatorName;

    @ApiModelProperty("申请时间-开始")
    private String startTime;

    @ApiModelProperty("申请时间-结束")
    private String endTime;

    @ApiModelProperty("待办/已办")
    private String type;

    @ApiModelProperty("uidList")
    private List uidList;
}
