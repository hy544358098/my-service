package com.crc.business.oa.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: Page
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 14:30
 * @Version: 1.0
 */
@Data
public class CommonParam {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("每页数据")
    private Integer pageSize=10;

    @ApiModelProperty("页大小")
    private Integer pageNum=1;

    @ApiModelProperty(value = "ldapId", hidden = true)
    private String ldapId;

    @ApiModelProperty(value = "角色名称", hidden = true)
    private String roleName;

    @ApiModelProperty(value = "申请日期")
    private String applyDate;

    @ApiModelProperty("统计城市公司")
    private String statisticsCityName;

    @ApiModelProperty("申请日期开始时间")
    private String startTime;

    @ApiModelProperty("申请日期结束时间")
    private String endTime;

    @ApiModelProperty("审批状态")
    private String approvalStatus;

}
