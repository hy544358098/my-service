package com.crc.business.independent.controller;

import com.crc.business.independent.model.entity.SupervisionDatabaseBusiness;
import com.crc.business.independent.model.vo.request.DatabaseBusinessParam;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.model.vo.result.SdbAndUpVo;
import com.crc.business.independent.service.SupervisionDatabaseBusinessService;
import com.crc.common.utils.ResultVoUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Description 业务观察
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("api/business")
public class SupervisionDatabaseBusinessController {
  @Autowired private SupervisionDatabaseBusinessService supervisionDatabaseBusinessService;


  @ApiOperation(value = "列表条件分页查询")
  @PostMapping(value = "/findPageList")
  public ResultVo findPageList(
          @RequestBody DatabaseBusinessParam supervisionDatabaseBusinessVo) {

    return supervisionDatabaseBusinessService.findPageList(supervisionDatabaseBusinessVo);
  }


  @ApiOperation(value = "详情查询")
  @GetMapping(value = "/findDetails")
  public ResultVo findDetails(@RequestParam Long id) {
    return supervisionDatabaseBusinessService.findDetails(id);
  }


  @ApiOperation(value = "单条查询")
  @RequestMapping("/info/{id}")
  public ResultVo info(@PathVariable("id") Long id) {
    SupervisionDatabaseBusiness byId = supervisionDatabaseBusinessService.getById(id);
    return ResultVoUtil.success(byId);
  }


  @ApiOperation(value = "新增")
  @RequestMapping("/add")
  public ResultVo save(@RequestBody SdbAndUpVo sdbAndUpVo) {
    supervisionDatabaseBusinessService.add(sdbAndUpVo);
    return ResultVoUtil.success();
  }


  @ApiOperation(value = "修改")
  @RequestMapping("/update")
  public ResultVo update(@RequestBody SdbAndUpVo sdbAndUpVo) {
    supervisionDatabaseBusinessService.updateSuAndUpVo(sdbAndUpVo);
    return ResultVoUtil.success();
  }


  @ApiOperation(value = "删除")
  @RequestMapping("/delete")
  public ResultVo delete(@RequestBody Long[] ids) {
    supervisionDatabaseBusinessService.removeByIds(Arrays.asList(ids));
    return ResultVoUtil.success();
  }



  @ApiOperation(value = "导出excel数据")
  @RequestMapping(value = "/export", method = RequestMethod.POST)
  public void supervisionExport(
          @RequestHeader String userId,
          @RequestBody DatabaseBusinessParam databaseBusinessSeachVo)
          throws IOException {
    databaseBusinessSeachVo.setLdapId(userId);
    supervisionDatabaseBusinessService.excel(SdbAndUpVo.class, databaseBusinessSeachVo);
  }


  @ApiOperation(value = "导入数据")
  @PostMapping("/importExcelWithSimple")
  public void importExcelWithSimple(
          MultipartFile file) {
    supervisionDatabaseBusinessService.importFile(file);
  }
}
