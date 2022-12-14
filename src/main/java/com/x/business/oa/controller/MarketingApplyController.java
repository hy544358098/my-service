package com.x.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.x.business.oa.model.vo.request.MarketingApplyParam;
import com.x.business.oa.service.MarketingApplyService;
import com.x.runwork.core.ssdp.ResponseData;
import com.x.runwork.core.ssdp.ReturnCode;
import com.x.runwork.core.ssdp.base.BaseController;
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
 * 营销预算执行申请
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-28 10:25:39
 */
@Slf4j
@RestController
@Api(value = "oa_营销预算执行申请", tags = "oa_营销预算执行申请")
@RequestMapping("api/marketing/apply")
public class MarketingApplyController extends BaseController {
    @Autowired
    private MarketingApplyService marketingApplyService;
    private Logger logger = LoggerFactory.getLogger(MarketingApplyController.class);


    @PostMapping("statistics")
    @ApiOperation("报表统计")
    public String statisticReport(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->"+ssdpVo.toJSONString());
        try {
            //获取业务参数
            MarketingApplyParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, MarketingApplyParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , marketingApplyService.statisticReport(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

}
