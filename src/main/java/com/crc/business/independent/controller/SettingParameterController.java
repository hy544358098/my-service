package com.crc.business.independent.controller;

import com.crc.business.independent.model.vo.request.SettingParamPO;
import com.crc.business.independent.model.vo.request.SettingParamSearch;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.SettingParameterService;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.ResultVoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SettingParameterController
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:00
 * @Version: 1.0
 */
@RestController
@Api(tags = "系统设置-参数设置",value = "系统设置-参数设置")
@RequestMapping("api/sys/setting/param")
public class SettingParameterController {
    @Autowired
    private SettingParameterService settingParameterService;

    @ApiOperation("新增和更新")
    @PostMapping
    private ResultVo save(@RequestBody SettingParamPO settingParam,@RequestHeader("userId") String userId) throws ServiceException {
        settingParameterService.save(settingParam,userId);
        return ResultVoUtil.success();
    }

    @ApiOperation("详情")
    @GetMapping("/{uid}")
    public ResultVo detail(@PathVariable("uid") String uid){
        return ResultVoUtil.success(settingParameterService.detail(uid));
    }

    @ApiOperation("删除")
    @PostMapping("delete")
    public ResultVo delete(@RequestBody List<String> list){
        settingParameterService.delete(list);
        return ResultVoUtil.success();
    }

    @ApiOperation("列表")
    @PostMapping("list")
    public ResultVo list(@RequestBody SettingParamSearch settingParamSearch){
        return ResultVoUtil.success(settingParameterService.list(settingParamSearch));
    }

    @ApiOperation("业务流程、条件类型、运算符下拉选")
    @GetMapping()
    private ResultVo selectList(){
        return ResultVoUtil.success(settingParameterService.selectList());
    }
}

