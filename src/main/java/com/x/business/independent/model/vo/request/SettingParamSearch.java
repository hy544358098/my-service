package com.x.business.independent.model.vo.request;

import com.x.business.oa.model.vo.request.CommonParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SettingParamSearchPO
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:46
 * @Version: 1.0
 */
@Data
public class SettingParamSearch extends CommonParam {
    @ApiModelProperty("业务流程字典id")
    private Integer dictionaryId;

    @ApiModelProperty("条件类型")
    private String type;
}
