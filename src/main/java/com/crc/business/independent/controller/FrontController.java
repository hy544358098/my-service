package com.crc.business.independent.controller;

import com.crc.business.independent.service.FrontService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/3 17:43
 * @Version: 1.0
 */
@RequestMapping("api/front")
@Api(tags = "首页管理", value = "首页管理")
@RestController
public class FrontController {
    @Autowired
    private FrontService frontService;
    private Logger logger = LoggerFactory.getLogger(FrontController.class);

    @PostMapping
    @ApiOperation("首页数据统计")
    public String findDataCount() {
        try {
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , frontService.findDataCount()).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
