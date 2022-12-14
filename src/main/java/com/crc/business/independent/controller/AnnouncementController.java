package com.crc.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.model.vo.request.AnnouncementParamPO;
import com.crc.business.independent.model.vo.request.AnnouncementSearchParam;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.AnnouncementService;
import com.crc.common.utils.ResultVoUtil;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AnnouncementController
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/18 15:51
 * @Version: 1.0
 */
@Slf4j
@RestController
@Api(value = "系统管理-公告管理", tags = "系统管理-公告管理")
@RequestMapping("api/sys/announcement")
public class AnnouncementController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
    @Autowired
    private AnnouncementService announcementService;

    @ApiOperation("公告新增和编辑接口")
    @PostMapping
    public ResultVo save(@RequestBody AnnouncementParamPO announcementParam, @RequestHeader("userId") String userId) {
        announcementService.save(announcementParam, userId);
        return ResultVoUtil.success();
    }

    @ApiOperation("删除")
    @PostMapping("delete")
    private ResultVo delete(@RequestBody List<String> list) {
        announcementService.delete(list);
        return ResultVoUtil.success();
    }

    @ApiOperation("列表")
    @PostMapping("list")
    private String list(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            AnnouncementSearchParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, AnnouncementSearchParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , announcementService.list(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
