package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.OrganizationLifeParam;
import com.crc.business.oa.service.OrganizationLifeService;
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
 * 党组织生活
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-29 16:07:12
 */
@Slf4j
@RestController
@Api(tags = "oa_党组织生活", value = "oa_党组织生活")
@RequestMapping("api/organization/life")
public class OrganizationLifeController extends BaseController {
    @Autowired
    private OrganizationLifeService organizationLifeService;
    private Logger logger = LoggerFactory.getLogger(OrganizationLifeController.class);

    @ApiOperation(value = "统计图表")
    @PostMapping("/statistics")
    public String statistics(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            OrganizationLifeParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, OrganizationLifeParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , organizationLifeService.statistics(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
