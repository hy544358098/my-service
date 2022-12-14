package com.crc.business.independent.controller;

import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.vo.result.DictionariesVo;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.TDictionariesDetailedService;
import com.crc.business.oa.controller.PurchaseSpecialController;
import com.crc.business.oa.model.vo.request.PurchaseSpecialParam;
import com.crc.common.utils.ResultVoUtil;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaoming
 * @date 2021-09-27 11:16:25
 */
@RestController
@RequestMapping("api/detailed")
public class TDictionariesDetailedController {
  @Autowired private TDictionariesDetailedService tDictionariesDetailedService;
  private Logger logger = LoggerFactory.getLogger(TDictionariesDetailedController.class);

  /** 列表明细查询 */
  @ApiOperation(value = "列表明细查询")
  @PostMapping(value = "/findDetailedList")
  public String findDetailedList() {
    try {
      return new ResponseData(ReturnCode.S000A000,ReturnCode.S000A000_DESC
              ,tDictionariesDetailedService.findDetailedList()).toString();
    } catch (Exception e) {
      logger.error("login error", e);
      return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
    }
  }

  /** 列表查询 */
  @ApiOperation(value = "列表查询")
  @PostMapping(value = "/findList")
  public ResultVo findList(@RequestBody DictionariesVo dictionariesVo) {
    return ResultVoUtil.success(tDictionariesDetailedService.findList(dictionariesVo));
  }

  /** 单条查询--查询明细列表 */
  @ApiOperation(value = "单条查询")
  @GetMapping("/info/{id}")
  public ResultVo info(@PathVariable("id") Integer id) {
    return ResultVoUtil.success(tDictionariesDetailedService.findByClassificationId(id));
  }

  @ApiOperation(value = "单条查询")
  @GetMapping("/findOne/{id}")
  public ResultVo findOne(@PathVariable("id") Integer id) {
    return ResultVoUtil.success(tDictionariesDetailedService.findOne(id));
  }

  @ApiOperation(value = "新增")
  @PostMapping("/add")
  public ResultVo save(@RequestBody TDictionariesDetailed tDictionariesDetailed, HttpServletRequest request) {
    String userId = request.getHeader("userId");
    tDictionariesDetailed.setCreatId(userId);
    return tDictionariesDetailedService.add(tDictionariesDetailed);
  }

  @ApiOperation(value = "修改")
  @RequestMapping("/update")
  public ResultVo update(@RequestBody TDictionariesDetailed tDictionariesDetailed, HttpServletRequest request) {
    String userId = request.getHeader("userId");
    tDictionariesDetailed.setUpdateId(userId);
    return tDictionariesDetailedService.updateDetailed(tDictionariesDetailed);
  }

  @ApiOperation(value = "删除")
  @RequestMapping("/delete")
  public ResultVo delete(@RequestBody Long[] ids) {
    return null;
  }
}
