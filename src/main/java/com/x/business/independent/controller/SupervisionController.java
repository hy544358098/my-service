package com.x.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.x.business.independent.model.vo.request.SupervisionParam;
import com.x.business.independent.model.vo.result.SuAndUpVo;
import com.x.business.independent.model.vo.result.SupervisionVo;
import com.x.business.independent.service.SupervisionService;
import com.x.common.utils.mail.SendEmailUtil;
import com.x.runwork.core.ssdp.ResponseData;
import com.x.runwork.core.ssdp.ReturnCode;
import com.x.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description 我要观察 @Author xiaoming @Date 2021/10/22 13:37 @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/supervision")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupervisionController extends BaseController {
    private final SupervisionService supervisionService;
    private final SendEmailUtil sendEmailUtil;
    private Logger logger = LoggerFactory.getLogger(SupervisionController.class);

    @ApiOperation(value = "测试定时任务")
    @PostMapping(value = "/text")
    public void text() throws ParseException {
        sendEmailUtil.timingRemind();
    }

    @ApiOperation(value = "列表条件分页查询")
    @PostMapping(value = "/findPageList")
    public String findPageList(
            @RequestHeader String userId,
            @RequestHeader String roleNames,
            @RequestBody SupervisionParam supervisionVo) {
            supervisionVo.setLdapId(userId);
            Page<SupervisionVo> list = supervisionService.list(supervisionVo, roleNames);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , list).toString();
    }

    @ApiOperation(value = "各状态数量查询")
    @PostMapping(value = "/findStatusNum")
    public String findStatusNum(
            @RequestHeader String userId,
            @RequestHeader String roleNames,
            @RequestBody SupervisionParam supervisionVo) {
            supervisionVo.setLdapId(userId);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , supervisionService.statisticalData(supervisionVo, roleNames)).toString();
    }

    @ApiOperation(value = "详情查询")
    @PostMapping(value = "/findDetails")
    public String findDetails(
            @RequestHeader String userId, @RequestHeader String roleNames,
            @RequestBody String id) {
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , supervisionService.findDetails(id, userId, roleNames)).toString();
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    @Transactional
    public String save(@RequestHeader String userId, @RequestBody SuAndUpVo suAndUpVo) {
            supervisionService.add(suAndUpVo, userId);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC).toString();
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    @Transactional
    public String update(@RequestBody JSONObject ssdpVo) {
            //获取业务参数
            SuAndUpVo param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, SuAndUpVo.class);
            if (param == null) {
                return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
            }
            supervisionService.update(param);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC,"成功").toString();
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    @Transactional
    public String delete(@RequestBody JSONObject ssdpVo) {
            //获取业务参数
            Long[] param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, Long[].class);
            if (param == null) {
                return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
            }
            supervisionService.removeByIds(Arrays.asList(param));
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC,"成功").toString();
    }

    @ApiOperation(value = "逻辑删除")
    @PostMapping("/false/delete")
    @Transactional
    public String falseDelete(@RequestBody Long[] ids) {
            supervisionService.falseDelete(Arrays.asList(ids));
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC).toString();
    }

    @ApiOperation(value = "统计图表一")
    @PostMapping("/statisticsOne")
    public String statisticsOne() {
            return new ResponseData
                    (ReturnCode.S000A000, ReturnCode.S000A000_DESC, supervisionService.statisticsOne()).toString();
    }

    @ApiOperation(value = "统计图表二")
    @PostMapping("/statisticsTwo")
    public String statisticsTwo() {
            return new ResponseData
                    (ReturnCode.S000A000, ReturnCode.S000A000_DESC, supervisionService.statisticsTwo()).toString();
    }

    @ApiOperation(value = "统计图表三")
    @PostMapping("/statisticsThree")
    public String statisticsThree() {
            return new ResponseData
                    (ReturnCode.S000A000, ReturnCode.S000A000_DESC, supervisionService.statisticsThree()).toString();
    }


    @ApiOperation(value = "置顶")
    @PostMapping("/top")
    @Transactional
    public String top(@RequestBody Map<String, String> map) {
        supervisionService.top(map.get("id"),Integer.valueOf(map.get("topType")));
        return new ResponseData
                (ReturnCode.S000A000, ReturnCode.S000A000_DESC,"成功").toString();
    }
}






