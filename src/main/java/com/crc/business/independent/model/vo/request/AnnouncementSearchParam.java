package com.crc.business.independent.model.vo.request;

import com.crc.business.oa.model.vo.request.CommonParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AnnouncementSearchParamPo
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/18 16:10
 * @Version: 1.0
 */
@Data
public class AnnouncementSearchParam extends CommonParam {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("重要/普通")
    private Integer type;

    @ApiModelProperty("是否置顶")
    private Integer topType;
}
