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
public class CloudSupplierParam extends CommonParam {

    @ApiModelProperty("组织")
    private String deptName;

    @ApiModelProperty("申请部门")
    private String applyDept;

    @ApiModelProperty("经办人")
    private String operatorName;

    @ApiModelProperty("预警流程节点")
    private String alertNode;

    @ApiModelProperty("是否已阅: 0 已阅 ；1 待阅")
    private String hasRead;

    @ApiModelProperty("观察状态:0 特别关注； 1 预警； 2 正常")
    private String monitorStatus;

}
