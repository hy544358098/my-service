package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.CloudSupplierStatisticsParam;
import com.crc.business.oa.service.CloudSupplierService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PurchaseShortlistedController
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/11 13:58
 * @Version: 1.0
 */
@RestController
@RequestMapping("api/purchase/cloud/supplier")
@Api(tags = "oa_采购云供方引进审批单", value = "oa_采购云供方引进审批单")
@Slf4j
public class CloudSupplierController extends BaseController {
    @Autowired
    private CloudSupplierService cloudSupplierService;


    @ApiOperation(value = "统计图表")
    @PostMapping(value = "/statistics", produces = "application/json")
    public String statistics(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            CloudSupplierStatisticsParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, CloudSupplierStatisticsParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , cloudSupplierService.statistics(param)).toString();
        } catch (Exception e) {
            log.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

}
