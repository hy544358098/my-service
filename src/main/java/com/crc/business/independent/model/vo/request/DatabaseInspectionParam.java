package com.crc.business.independent.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 观察数据库---纪检模块搜索参数
 *  @author xiaoming
 */
@Data
public class DatabaseInspectionParam {

    @ApiModelProperty("ldapId")
    private String ldapId;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("办理状态")
    private String handleStatus;

    @ApiModelProperty("案件编号")
    private String caseNumber;

    @ApiModelProperty("问题线索涉及领域")
    private String problemClueField;

    @ApiModelProperty("违纪违规类别")
    private String violationType;

    @ApiModelProperty("处分类别")
    private String punishmentType;

    @ApiModelProperty("起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String endTime;

    @ApiModelProperty("页")
    private Integer pageNum;

    @ApiModelProperty("行")
    private Integer pageSize;



}
