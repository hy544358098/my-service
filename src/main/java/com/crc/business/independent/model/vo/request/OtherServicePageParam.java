package com.crc.business.independent.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangxy
 * @Classname OtherServicePageParam
 * @Date 2021/3/26 9:22 上午
 */
@Data
@ApiModel(value = "OtherServicePageParam",description = "其他服务查询条件")
public class OtherServicePageParam extends RequestVo {

    /**
     * 服务编码
     */
    @ApiModelProperty(value = "服务编码")
    private String serviceCode;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    private String serviceName;


}
