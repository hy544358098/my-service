package com.crc.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.model.entity.OperationDetail;
import com.crc.business.independent.model.vo.result.OperationDetailVo;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.model.vo.result.SuAndUpVo;
import com.crc.business.independent.service.OperationDetailService;
import com.crc.business.independent.service.SupervisionService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * @Description 观察操作表
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@RestController
@RequestMapping("api/operationdetail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationDetailController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(OperationDetailController.class);
    private final OperationDetailService operationDetailService;
    private final SupervisionService supervisionService;



    @ApiOperation(value = "单条查询")
    @RequestMapping(value = "/info/{id}")
    public ResultVo info(@PathVariable("id") Integer id) {
        OperationDetail operationDetail = operationDetailService.getById(id);
        ResultVo resultVo = new ResultVo();
        resultVo.setData(operationDetail);
        return resultVo;
    }

    @ApiOperation(value = "存储")
    @RequestMapping("/save")
    public ResultVo save(@RequestBody OperationDetail operationDetail) {
        operationDetailService.save(operationDetail);
        return new ResultVo();
    }

    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public ResultVo update(@RequestBody OperationDetail operationDetail) {
        operationDetailService.updateById(operationDetail);
        return new ResultVo();
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    public ResultVo delete(@RequestBody Integer[] ids) {
        operationDetailService.removeByIds(Arrays.asList(ids));
        return new ResultVo();
    }

    @ApiOperation(value = "操作")
    @PostMapping("/operation")
    public String operation(@RequestBody JSONObject ssdpVo) {
            //获取业务参数
            OperationDetailVo param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, OperationDetailVo.class);
            if (param == null) {
                return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
            }
            // 修改观察表数据
            supervisionService.operation(param);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC,"成功").toString();
    }
}
