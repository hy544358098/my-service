package com.x.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.x.business.independent.model.entity.TDictionariesDetailed;
import com.x.business.independent.model.vo.result.DictionariesVo;
import com.x.business.independent.model.vo.result.ResultVo;

import java.util.List;

/**
 * @author xiaoming
 * @date 2021-09-27 11:16:25
 */
public interface TDictionariesDetailedService extends IService<TDictionariesDetailed> {

  /**
   * 列表查询
   *
   * @return
   */
  ResultVo findDetailedList();

  /**
   * 单条查询
   *
   * @return
   */
  List<TDictionariesDetailed> findByClassificationId(Integer id);

  /**
   * 新增
   *
   * @return
   */
  ResultVo add(TDictionariesDetailed tDictionariesDetailed);

  /**
   * 修改
   *
   * @return
   */
  ResultVo updateDetailed(TDictionariesDetailed tDictionariesDetailed);

  /**
   * 查询列表
   *
   * @return
   */
  ResultVo findList(DictionariesVo dictionariesVo);

  /** 单条查询--查询单条明细 */
  Object findOne(Integer id);
}
