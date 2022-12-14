package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.PurchaseShortListedParam;
import com.crc.business.oa.service.PurchaseShortlistedService;
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
 * @ClassName: PurchaseShortlistedController
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/11 13:58
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("api/purchase/shortlisted")
@Api(tags = "oa_投标入围单位审批", value = "oa_投标入围单位审批")
public class PurchaseShortlistedController extends BaseController {
    @Autowired
    private PurchaseShortlistedService shortlistedService;
    private Logger logger = LoggerFactory.getLogger(PurchaseShortlistedController.class);

    @PostMapping("statistics")
    @ApiOperation("报表统计")
    public String statisticReport(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            PurchaseShortListedParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, PurchaseShortListedParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , shortlistedService.statisticReport(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

}
