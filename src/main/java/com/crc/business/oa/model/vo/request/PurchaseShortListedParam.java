package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ShortListPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:27
 * @Version: 1.0
 */
@Data
public class PurchaseShortListedParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("组织")
    private String organization;

    @ApiModelProperty("经办人")
    private String applyName;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅")
    private String hasRead;

    @ApiModelProperty("观察状态")
    private String monitorStatus;

    @ApiModelProperty("单位名称")
    private String companyName;

    @ApiModelProperty("招标名称")
    private String biddingName;

    @ApiModelProperty("对应公司")
    private String forCompany;

    @ApiModelProperty("采购方案名称")
    private String purchaseSortName;

    @ApiModelProperty("采购组织")
    private String purchaseOrganization;

}
