package com.crc.business.independent.controller;

import com.crc.business.independent.model.entity.TDictionariesClassification;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.TDictionariesClassificationService;
import com.crc.common.utils.ResultVoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoming
 * @date 2021-09-27 11:16:25
 */
@RestController
@RequestMapping("api/classification")
public class TDictionariesClassificationController {
  @Autowired
  private TDictionariesClassificationService tDictionariesClassificationService;

  /** 列表查询 */
  @ApiOperation(value = "列表查询")
  @GetMapping(value = "/findList")
  public ResultVo findPageList() {
    List<TDictionariesClassification> list = tDictionariesClassificationService.list();
    return ResultVoUtil.success(list);
  }
}
