package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 法律合规-案件处理方案审批
 * @Date: 2021/10/12 14:27
 * @Version: 1.0
 */
@Data
public class LawCaseFilingApprovalParam extends CommonParam {

    @ApiModelProperty("单据编号")
    private String documentNumber;

    @ApiModelProperty("城市公司")
    private String cityFirm;

    @ApiModelProperty("主办单位")
    private String organizer;

    @ApiModelProperty("最高院案由")
    private String supremeCourtCause;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅: read已阅； beread待阅")
    private String hasRead;

    @ApiModelProperty("special_attention 特别关注；warning 预警；normal正常")
    private String monitorStatus;

}
