package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.DesignChangeParam;
import com.crc.business.oa.service.DesignChangeService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description OA设计变更审批
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/toadesignChange")
@Api(tags = "oa_设计变更审批", value = "oa_设计变更审批")
public class DesignChangeController extends BaseController {
    @Autowired
    private DesignChangeService designChangeService;
    private Logger logger = LoggerFactory.getLogger(DesignChangeController.class);

    @PostMapping("statistics")
    @ApiOperation("报表统计")
    public String statisticReport(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        //获取业务参数
        try {
            DesignChangeParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, DesignChangeParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , designChangeService.statisticReport(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
