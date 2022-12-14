package com.x.business.oa.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 投标入围单位审批——资格入围结果实体
 * @Author YANKAIBO
 * @Date 2021/11/29 15:56
 * @Version 1.0
 */
@Data
public class QualificationFinalistResultsData {

    @ApiModelProperty("备注")
    public String remark;

    @ApiModelProperty("是否在册")
    public String registered;

    @ApiModelProperty("二级单位")
    public String unit;

    @ApiModelProperty("供方资格审查结果")
    public String grade;

    @ApiModelProperty("项目经理")
    public String manger;

    @ApiModelProperty("是否入围")
    public String selected;

    @ApiModelProperty("供应商名称")
    public String name;

    @ApiModelProperty("评分结果")
    public String result;
}
