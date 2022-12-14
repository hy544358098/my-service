package com.crc.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.oa.model.vo.request.PendingParam;
import com.crc.business.oa.service.PendingService;
import com.crc.common.utils.ResultVoUtil;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 我的待办
 * @Author xiaoming
 * @Date 2021/12/2 15:25
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/pending")
@Api(tags = "oa_我的待办", value = "oa_我的待办")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PendingController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PendingController.class);
    private final PendingService pendingService;

    @ApiOperation(value = "列表条件分页查询")
    @PostMapping(value = "/list")
    public String list(@RequestHeader String userId,
                       @RequestHeader String roleNames, @RequestBody PendingParam pendingParam) {
        try {
            pendingParam.setLdapId(userId);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , pendingService.list(pendingParam, roleNames)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }


    @ApiOperation(value = "知会列表条件分页查询")
    @PostMapping(value = "/informList")
    public String informList(@RequestHeader String userId, @RequestBody PendingParam pendingParam){
        try {
        pendingParam.setLdapId(userId);
        return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                , pendingService.informList(pendingParam)).toString();
    } catch (Exception e) {
        logger.error("login error", e);
        return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
    }
    }
}
