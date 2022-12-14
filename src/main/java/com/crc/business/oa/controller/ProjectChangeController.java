package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.oa.model.vo.request.ProjectChangeParam;
import com.crc.business.oa.service.ProjectChangeService;
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
 * @Description OA工程变更
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/toaprojectChange")
@Api(tags = "oa_工程变更", value = "oa_工程变更")
public class ProjectChangeController extends BaseController {
    @Autowired
    private ProjectChangeService projectChangeService;
    private Logger logger = LoggerFactory.getLogger(ProjectChangeController.class);


    @ApiOperation(value = "统计图表")
    @PostMapping("/statistics")
    public String statistics(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            ProjectChangeParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, ProjectChangeParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , projectChangeService.statistics(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
