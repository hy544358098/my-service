package com.x.business.independent.controller;

import com.x.business.independent.model.vo.request.DatabaseInspectionParam;
import com.x.business.independent.model.vo.result.ResultVo;
import com.x.business.independent.model.vo.result.SdIAndUpVo;
import com.x.business.independent.service.SupervisionDatabaseInspectionService;
import com.x.common.utils.ResultVoUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Description 纪检观察
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("api/inspection")
public class SupervisionDatabaseInspectionController {
  @Autowired private SupervisionDatabaseInspectionService supervisionDatabaseService;

  @ApiOperation(value = "列表条件分页查询")
  @PostMapping(value = "/findPageList")
  public ResultVo findPageList(@RequestBody DatabaseInspectionParam supervisionDatabaseVo) {

    return supervisionDatabaseService.findPageList(supervisionDatabaseVo);
  }


  @ApiOperation(value = "详情查询")
  @GetMapping(value = "/findDetails")
  public ResultVo findDetails(@RequestParam Long id) {
    return supervisionDatabaseService.findDetails(id);
  }



  @ApiOperation(value = "新增")
  @RequestMapping("/add")
  public ResultVo save(@RequestBody SdIAndUpVo sdIAndUpVo) {
    supervisionDatabaseService.add(sdIAndUpVo);
    return ResultVoUtil.success();
  }


  @ApiOperation(value = "修改")
  @RequestMapping("/update")
  public ResultVo update(@RequestBody SdIAndUpVo sdIAndUpVo) {
    supervisionDatabaseService.updateSuAndUpVo(sdIAndUpVo);
    return ResultVoUtil.success();
  }


  @ApiOperation(value = "删除")
  @RequestMapping("/delete")
  public ResultVo delete(@RequestBody Long[] ids) {
    supervisionDatabaseService.removeByIds(Arrays.asList(ids));
    return ResultVoUtil.success();
  }

  @ApiOperation(value = "导出excel数据")
  @RequestMapping(value = "/export", method = RequestMethod.POST)
  public void supervisionExport(
          @RequestHeader String userId,
          @RequestBody DatabaseInspectionParam databaseInspectionSeachVo)
          throws IOException {
    databaseInspectionSeachVo.setLdapId(userId);
    supervisionDatabaseService.excel(SdIAndUpVo.class, databaseInspectionSeachVo);
  }


  @ApiOperation(value = "导入数据")
  @PostMapping("/importExcelWithSimple")
  public void importExcelWithSimple(@RequestParam("file") MultipartFile file) {
    supervisionDatabaseService.importFile(file);
  }

  @ApiOperation(value = "统计图表一")
  @GetMapping("/statisticsOne")
  public ResultVo statisticsOne() {
    return ResultVoUtil.success(supervisionDatabaseService.statisticsOne());
  }

  @ApiOperation(value = "统计图表二")
  @GetMapping("/statisticsTwo")
  public ResultVo statisticsTwo() {
    return ResultVoUtil.success(supervisionDatabaseService.statisticsTwo());
  }

  @ApiOperation(value = "统计图表三")
  @GetMapping("/statisticsThree")
  public ResultVo statisticsThree() {
    return ResultVoUtil.success(supervisionDatabaseService.statisticsThree());
  }

  @ApiOperation(value = "统计图表四")
  @GetMapping("/statisticsFour")
  public ResultVo statisticsFour() {
    return ResultVoUtil.success(supervisionDatabaseService.statisticsFour());
  }
}
