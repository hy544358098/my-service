package com.crc.business.oa.model.vo.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author xiaoming
 * @Description TODO
 * @date 2021/9/27 19:36
 */
@Data
public class OaTableInfo {
    /***
     * 表格名称
     */
    String tableName;

    /***
     * 表格详情
     */
    List<JSONObject> tableDetails;
}
