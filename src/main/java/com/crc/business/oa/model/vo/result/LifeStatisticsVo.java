package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/9 16:15
 * @Version 1.0
 */
@Data
public class LifeStatisticsVo {

    @ApiModelProperty("党员数")
    private String martyMemberTotal;

    @ApiModelProperty("党委数")
    private String partyCommittee;

    @ApiModelProperty("党总支数")
    private String partyTotalBranch;

    @ApiModelProperty("党支部数")
    private String partyBranch;

    @ApiModelProperty("组织生活次数")
    private String organizationalLifeTotal;

    @ApiModelProperty("主题党日")
    private String themeParty;

    @ApiModelProperty("组织生活会")
    private String organizationalLife;

    @ApiModelProperty("支部党员大会")
    private String branchPartyMember;

    @ApiModelProperty("支部委员会")
    private String branchCommittee;

    @ApiModelProperty("党小组会")
    private String partyGroup;

    @ApiModelProperty("党课")
    private String partyClass;
}
