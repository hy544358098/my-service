package com.x.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/3 17:55
 * @Version: 1.0
 */
@Data
public class BusinessDataCount {
    @ApiModelProperty("采购方式变更及单一来源定标申请")
    private Integer singlesourceCount;
    @ApiModelProperty("采购云供方引进审批单")
    private Integer cloudSupplierCount;
    @ApiModelProperty("投标入围单位审批")
    private Integer shareListedCount;
    @ApiModelProperty("特殊资源比选审批")
    private Integer specialsourcedCount;
    @ApiModelProperty("工程争议索赔")
    private Integer disputeclaimsCount;
    @ApiModelProperty("案件处理方案审批")
    private Integer casefilingapprovalCount;
    @ApiModelProperty("设计变更")
    private Integer designChangeCount;
    @ApiModelProperty("工程变更")
    private Integer projectChangeCount;
    @ApiModelProperty("营销预算执行申请")
    private Integer marketingApplyCount;
    @ApiModelProperty("营销年度预算调整")
    private Integer annualBudgetCount;
    @ApiModelProperty("营销进度熔断重启")
    private Integer marketingFuseCount;
    @ApiModelProperty("党组织生活")
    private Integer organizationLifeCount;

}
