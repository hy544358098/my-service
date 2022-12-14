package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/11 17:24
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum OaTableNameEnum {
    /***
     * 采购管理_采购方式变更及单一来源定标申请
     */
    OA_PROCESS_PURCHASE_SINGLE_SOURCE("t_oa_purchase_singlesource", "purchase_singlesource"),

    /***
     * 采购管理_特殊资源比选审批
     */
    OA_PURCHASE_SPECIAL_SOURCE("t_oa_purchase_special", "purchase_specialsource"),

    /***
     * 成本合约_工程争议索赔
     */
    OA_COST_DISPUTE_CLAIMS("t_oa_cost_disputeclaims", "cost_disputeclaims"),

    /***
     * 工程变更
     */
    OA_PROJECT_CHANGE("t_oa_project_change", "project_change"),

    /**
     * 采购管理_投标入围单位审批
     */
    OA_PURCHASE_SHORTLISTED("t_oa_purchase_shortlisted", "purchase_shortlisted"),

    /**
     * 采购管理_采购云供方引进审批单
     */
    OA_PURCHASE_CLOUD_SUPPLIER("t_oa_purchase_cloud_supplier", "purchase_cloud_supplier"),

    /**
     * 营销年度预算调整
     */
    OA_MARKET_ANNUAL_BUDGET("t_oa_marketing_annual_budget", "marketing_annual_budget"),

    /**
     * 法律合规_案件处理方案审批
     */
    OA_LAW_CASE_FILING_APPROVAL("t_oa_law_casefilingapproval", "law_casefilingapproval"),

    /***
     * 设计管理_设计变更
     */
    OA_DESIGN_CHANGE("t_oa_design_change", "design_change"),

    /***
     * 营销管理_营销预算执行申请
     */
    OA_MARKETING_APPLY("t_oa_marketing_apply", "marketing_apply"),

    /***
     * 营销_进度熔断重启
     */
    OA_MARKETING_FUSE("t_oa_marketing_fuse", "marketing_fuse"),

    /***
     * 党组织生活
     */
    OA_ORGANIZATION_LIFE("t_oa_organization_life", "organization_life");

    private String table;

    private String name;
}
