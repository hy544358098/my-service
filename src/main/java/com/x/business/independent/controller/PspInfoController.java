package com.x.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.x.business.independent.model.pojo.psp.PspBaseRes;
import com.x.business.independent.model.pojo.psp.PspOrgTreeReq;
import com.x.business.independent.model.pojo.psp.PspUserReq;
import com.x.business.independent.thirdservice.PspInfoService;
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
 * @author xiaoming @Description PSP服务
 * @date 2021/9/26 18:25
 */
@Api(tags = "PSP服务")
@RestController
@RequestMapping("/api/psp")
@Slf4j
public class PspInfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PspInfoController.class);
    @Autowired
    private PspInfoService pspInfoService;

    @ApiOperation(value = "获取用户权限接口")
    @PostMapping(value = "/getUserInfo")
    public String getUserInfo(@RequestBody JSONObject ssdpVo) {
        PspUserReq pspUserReq = this.getBusDataBase64DecodeToJavaObject(ssdpVo, PspUserReq.class);
        if (pspUserReq == null) {
            return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
        }
        try {
            PspBaseRes userInfo = pspInfoService.getUserInfo(pspUserReq.getLoginName());
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , userInfo.getData()).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

    @ApiOperation(value = "获取组织节点信息")
    @PostMapping(value = "/getOrgTree")
    public String getOrgTree(@RequestBody JSONObject ssdpVo) {
        PspOrgTreeReq pspOrgTreeReq = this.getBusDataBase64DecodeToJavaObject(ssdpVo, PspOrgTreeReq.class);
        if (pspOrgTreeReq == null) {
            return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
        }
        try {
            PspBaseRes userInfo = pspInfoService.getOrgTree(pspOrgTreeReq);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , userInfo.getData()).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
