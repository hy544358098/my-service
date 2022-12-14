package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.PurchaseSinglesourceParam;
import com.crc.business.oa.service.PurchaseSinglesourceService;
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
 * @Description 采购方式变更及单一来源定标申请
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/purchaseSinglesource")
@Api(tags = "oa_采购方式变更及单一来源定标申请", value = "oa_采购方式变更及单一来源定标申请")
public class PurchaseSinglesourceController extends BaseController {
    @Autowired
    private PurchaseSinglesourceService tOaPurchaseService;
    private Logger logger = LoggerFactory.getLogger(PurchaseSinglesourceController.class);

    @ApiOperation(value = "统计图表")
    @PostMapping("/statistics")
    public String statistics(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            PurchaseSinglesourceParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, PurchaseSinglesourceParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , tOaPurchaseService.statistics(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
