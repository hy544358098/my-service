package com.crc.business.oa.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: PurchaseShortListCountVO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/13 15:47
 * @Version: 1.0
 */
@Data
public class OaCountVO {
    @ApiModelProperty("总数")
    private int total;

    @ApiModelProperty("待阅")
    private int beread;

    @ApiModelProperty("特别关注")
    private int  specialAttention;

    @ApiModelProperty("预警")
    private int  warning;

    @ApiModelProperty("正常")
    private int normal;
}
