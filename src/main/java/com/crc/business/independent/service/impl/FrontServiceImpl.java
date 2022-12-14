package com.crc.business.independent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.dao.FrontDao;
import com.crc.business.independent.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 首页接口service层
 * @Author: xiaoming
 * @Date: 2021/11/3 17:45
 * @Version: 1.0
 */
@Service
public class FrontServiceImpl implements FrontService {
    @Autowired
    private FrontDao frontDao;
    @Override
    public JSONObject findDataCount() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("cityDataCount",frontDao.cityDataCount());
        jsonObject.put("ownerDataCount",frontDao.ownerDataCount());
        jsonObject.put("businessDataCount",frontDao.businessDataCount());
        jsonObject.put("StatusCount",frontDao.statusCount());
        return jsonObject;
    }
}
