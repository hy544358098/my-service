package com.x.business.oa.model.pojo;

import lombok.Data;
import lombok.NonNull;

/**
 * OA同步数据通用接口请求体
 *
 * @author YANKAIBO
 */
@Data
public class OaRequestData {

    /**
     * 流程类型，用于区分不同流程
     */
    @NonNull
    String processType;

    /**
     * 主表单信息，每个流程字段不同
     */
    @NonNull
    Object formInfo;

    /**
     * 公共信息
     */
    @NonNull
    CommonInfo commonInfo;
}
