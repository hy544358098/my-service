package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: MarketAnnualBudgetParamPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:27
 * @Version: 1.0
 */
@Data
public class MarketAnnualBudgetParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("预算名称")
    private String budgetName;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅: 0 已阅 ；1 待阅")
    private String hasRead;

    @ApiModelProperty("观察状态:0 特别关注； 1 预警； 2 正常")
    private String monitorStatus;
}
