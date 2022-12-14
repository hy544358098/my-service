package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 成本合约—工程争议索赔
 * @Date: 2021/10/12 14:27
 * @Version: 1.0
 */
@Data
public class CostDisputeClaimsParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅: read已阅； beread待阅")
    private String hasRead;

    @ApiModelProperty("special_attention 特别关注；warning 预警；normal正常")
    private String monitorStatus;
}
