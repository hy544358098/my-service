package com.crc.business.independent.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/3 17:45
 * @Version: 1.0
 */
public interface FrontService {
    /**
     * @return com.alibaba.fastjson.JSONObject
     * @Description 首页数据统计
     * @Date 2021/11/3 17:48
     * @Return
     */
    JSONObject findDataCount();
}
