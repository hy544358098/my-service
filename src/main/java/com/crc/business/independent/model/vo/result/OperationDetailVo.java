package com.crc.business.independent.model.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author BEYONDSOFT-101
 */
@Data
public class OperationDetailVo {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("观察表id")
    private Integer supervisionId;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("操作人id")
    private String operationPeopleId;

    @ApiModelProperty("操作人")
    private String operationPeople;

    @ApiModelProperty("被操作人id人集合")
    private List<String> byOperationPeopleIdList;

    @ApiModelProperty("被操作人id")
    private String byOperationPeopleId;

    @ApiModelProperty("被操作人")
    private String byOperationPeople;

    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operationTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("问题线索及领域")
    private String problemClueField;

    @ApiModelProperty("办结结果")
    private String finishType;

    @ApiModelProperty("链接表路径集合")
    private List<Integer> fileId;
}
