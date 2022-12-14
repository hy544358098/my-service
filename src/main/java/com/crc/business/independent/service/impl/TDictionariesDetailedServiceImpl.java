package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.TDictionariesClassificationDao;
import com.crc.business.independent.dao.TDictionariesDetailedDao;
import com.crc.business.independent.model.entity.TDictionariesClassification;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.vo.result.DictionariesNewVo;
import com.crc.business.independent.model.vo.result.DictionariesVo;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.TDictionariesDetailedService;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.utils.ResultVoUtil;
import com.crc.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @author xiaoming */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TDictionariesDetailedServiceImpl
    extends ServiceImpl<TDictionariesDetailedDao, TDictionariesDetailed>
    implements TDictionariesDetailedService {

  private final TDictionariesDetailedDao tDictionariesDetailedDao;
  private final TDictionariesClassificationDao tDictionariesClassificationDao;

  /**
   * 列表查询
   *
   * @return
   */
  @Override
  public ResultVo findDetailedList() {
    QueryWrapper<TDictionariesClassification> tcQueryWrapper = new QueryWrapper<>();
    tcQueryWrapper.orderByDesc("id");
    List<TDictionariesClassification> tDictionariesClassifications =
        tDictionariesClassificationDao.selectList(tcQueryWrapper);
    // 封装返回值
    List<DictionariesNewVo> list = new ArrayList<>();
    for (TDictionariesClassification tc : tDictionariesClassifications) {
      DictionariesNewVo dictionariesNewVo = new DictionariesNewVo();
      // 查询明细
      List<TDictionariesDetailed> byClassificationId = findByClassificationId(tc.getId());
      dictionariesNewVo.setTDictionariesClassification(tc);
      dictionariesNewVo.setList(byClassificationId);
      list.add(dictionariesNewVo);
    }
    return ResultVoUtil.success(list);
  }

  /**
   * 单条查询
   *
   * @return
   */
  @Override
  public List<TDictionariesDetailed> findByClassificationId(Integer id) {
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("classification_id", id);
    tdQueryWrapper.orderByDesc("id");
    List<TDictionariesDetailed> tDictionariesDetaileds =
        tDictionariesDetailedDao.selectList(tdQueryWrapper);
    return tDictionariesDetaileds;
  }

  /**
   * 新增
   *
   * @return
   */
  @Override
  public ResultVo add(TDictionariesDetailed tDictionariesDetailed) {
    // 判断重复
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = null;
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("classification_id",tDictionariesDetailed.getClassificationId());
    tdQueryWrapper.eq("type_code", tDictionariesDetailed.getTypeCode());
    List<TDictionariesDetailed> tDictionariesDetaileds =
        tDictionariesDetailedDao.selectList(tdQueryWrapper);
    if (!ObjectUtils.isEmpty(tDictionariesDetaileds)) {
      throw new ServiceException(ErrorCodeEnum.CODE_REPEAT);
    }

    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("classification_id",tDictionariesDetailed.getClassificationId());
    tdQueryWrapper.eq("type_name", tDictionariesDetailed.getTypeName());
    List<TDictionariesDetailed> tDictionariesDetailedsNew =
        tDictionariesDetailedDao.selectList(tdQueryWrapper);
    if (!ObjectUtils.isEmpty(tDictionariesDetailedsNew)) {
      throw new ServiceException(ErrorCodeEnum.NAME_REPEAT);
    }

    tDictionariesDetailed.setCreatTime(new Date());
    tDictionariesDetailedDao.insert(tDictionariesDetailed);
    return ResultVoUtil.success();
  }

  /**
   * 修改
   *
   * @return
   */
  @Override
  public ResultVo updateDetailed(TDictionariesDetailed tDictionariesDetailed) {
    // 判断重复
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = null;
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("classification_id",tDictionariesDetailed.getClassificationId());
    tdQueryWrapper.eq("type_code", tDictionariesDetailed.getTypeCode());
    TDictionariesDetailed tDictionariesDetaileds =
        tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    //type_code已有并且不是当前id
    if (ObjectUtils.isNotEmpty(tDictionariesDetaileds)&&!tDictionariesDetailed.getId().equals(tDictionariesDetaileds.getId())) {
      throw new ServiceException(ErrorCodeEnum.CODE_REPEAT);
    }

    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("classification_id",tDictionariesDetailed.getClassificationId());
    tdQueryWrapper.eq("type_name", tDictionariesDetailed.getTypeName());
    TDictionariesDetailed tDictionariesDetailedsNew =
        tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    //type_name已有并且不是当前id
    if (ObjectUtils.isNotEmpty(tDictionariesDetailedsNew)&&!tDictionariesDetailed.getId().equals(tDictionariesDetailedsNew.getId())) {
      throw new ServiceException(ErrorCodeEnum.NAME_REPEAT);
    }

    tDictionariesDetailed.setUpdateTime(new Date());
    tDictionariesDetailedDao.updateById(tDictionariesDetailed);
    return ResultVoUtil.success();
  }

  /**
   * 查询列表
   *
   * @return
   */
  @Override
  public ResultVo findList(DictionariesVo dictionariesVo) {
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    if (null != dictionariesVo.getClassificationId()) {
      tdQueryWrapper.eq("classification_id", dictionariesVo.getClassificationId());
    }
    if (!StringUtils.isEmpty(dictionariesVo.getTypeCode())) {
      tdQueryWrapper.like("type_code", dictionariesVo.getTypeCode());
    }
    if (!StringUtils.isEmpty(dictionariesVo.getTypeName())) {
      tdQueryWrapper.like("type_name", dictionariesVo.getTypeName());
    }
    tdQueryWrapper.orderByDesc("id");
    Page<TDictionariesDetailed> tdjectPage =
        new Page<>(dictionariesVo.getPageNum(), dictionariesVo.getPageSize());
    Page<TDictionariesDetailed> tDictionariesDetailedPage =
        tDictionariesDetailedDao.selectPage(tdjectPage, tdQueryWrapper);
    return ResultVoUtil.success(tDictionariesDetailedPage);
  }

  /** 单条查询--查询单条明细 */
  @Override
  public Object findOne(Integer id) {
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("id", id);
    TDictionariesDetailed tDictionariesDetailed =
        tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    return tDictionariesDetailed;
  }
}
