package com.crc.business.independent.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.SettingParameterDao;
import com.crc.business.independent.model.entity.SettingParameter;
import com.crc.business.independent.model.entity.SettingParameterCondition;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.vo.request.SettingParamPO;
import com.crc.business.independent.model.vo.request.SettingParamSearch;
import com.crc.business.independent.model.vo.result.SettingParameterListVo;
import com.crc.business.independent.model.vo.result.SettingParameterVo;
import com.crc.business.independent.service.SettingParameterConditionService;
import com.crc.business.independent.service.SettingParameterService;
import com.crc.business.independent.service.TDictionariesDetailedService;
import com.crc.common.enums.BusinessProcessEnum;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.exception.ServiceException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crc.business.oa.service.impl.CommonServiceImpl.countMap;
import static com.crc.business.oa.service.impl.CommonServiceImpl.extractMap;

/**
 * @ClassName: SettingParameterServiceImpl @Description: TODO @Author: xiaoming @Date: 2021/10/19
 * 14:03 @Version: 1.0
 */
@Service
public class SettingParameterServiceImpl extends ServiceImpl<SettingParameterDao, SettingParameter>
    implements SettingParameterService {
  @Autowired private SettingParameterDao settingParameterDao;
  @Autowired private SettingParameterConditionService settingParameterConditionService;

  @Autowired private TDictionariesDetailedService dictionariesDetailedService;

  @Value("${business.process.code}")
  private String businessProcessCode;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(SettingParamPO settingParam, String userId) throws ServiceException {
    SettingParameter settingParameter = new SettingParameter();
    BeanUtils.copyProperties(settingParam, settingParameter);
    if (StringUtils.isNotEmpty(settingParam.getUid())) {
      SettingParameter parameter =
          getOne(
              new QueryWrapper<SettingParameter>()
                  .eq("dictionary_id", settingParam.getDictionaryId())
                  .eq("type", settingParam.getType())
                  .ne("uid", settingParam.getUid()));
      if (ObjectUtils.isNotEmpty(parameter)) {
        throw new ServiceException(ErrorCodeEnum.DATA_NOT_REPEAT);
      }
      settingParameter.setUpdateTime(LocalDateTime.now());
      settingParameter.setUpdateUser(userId);
      settingParameter.update(
          new QueryWrapper<SettingParameter>().eq("uid", settingParam.getUid()));
      List<String> list = new ArrayList<>();
      list.add(settingParam.getUid());
      settingParameterConditionService.delete(list);
    } else {
      SettingParameter parameter =
          getOne(
              new QueryWrapper<SettingParameter>()
                  .eq("dictionary_id", settingParam.getDictionaryId())
                  .eq("type", settingParam.getType()));
      if (ObjectUtils.isNotEmpty(parameter)) {
        throw new ServiceException(ErrorCodeEnum.DATA_NOT_REPEAT);
      }
      settingParameter.setCreateTime(LocalDateTime.now());
      settingParameter.setUpdateTime(settingParameter.getCreateTime());
      settingParameter.setCreateUser(userId);
      settingParameter.setUpdateUser(userId);
      settingParameter.setUid(IdUtil.createSnowflake(1, 1).nextIdStr());
      settingParameter.insert();
    }
    if (!CollectionUtils.isEmpty(settingParam.getList())) {
      settingParam.setUid(settingParameter.getUid());
      saveParamCondition(settingParam, userId);
    }

    // 更改列表抽取比例
    if (StringUtils.equals(
        settingParam.getType(),
        BusinessProcessEnum.ConditionType.STATUS_EXTRACT_PROPORTION.getCode())) {
      SettingParamSearch settingParamSearch = new SettingParamSearch();
      settingParamSearch.setType(
          BusinessProcessEnum.ConditionType.STATUS_EXTRACT_PROPORTION.getCode());
      settingParamSearch.setDictionaryId(settingParam.getDictionaryId());
      List<SettingParameterListVo> list = settingParameterDao.getListByType(settingParamSearch);
      list.stream()
          .forEach(
              settingParameterListVo -> {
                extractMap.put(
                    settingParameterListVo.getTypeCode(),
                    settingParameterListVo.getExtractAmount());
                countMap.put(settingParameterListVo.getTypeCode(),0);
              });
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public void saveParamCondition(SettingParamPO settingParam, String userId) {
    List<SettingParameterCondition> settingParameterConditions = new ArrayList<>();
    settingParam.getList().stream()
        .forEach(
            settingParameterConditionOld -> {
              SettingParameterCondition settingParameterCondition = new SettingParameterCondition();
              BeanUtils.copyProperties(settingParameterConditionOld, settingParameterCondition);
              settingParameterCondition.setUid(IdUtil.createSnowflake(1, 1).nextIdStr());
              settingParameterCondition.setParamId(settingParam.getUid());
              settingParameterCondition.setUpdateTime(LocalDateTime.now());
              settingParameterCondition.setCreateTime(LocalDateTime.now());
              settingParameterCondition.setCreateUser(userId);
              settingParameterCondition.setUpdateUser(userId);
              settingParameterConditions.add(settingParameterCondition);
            });
    settingParameterConditionService.save(settingParameterConditions);
  }

  @Override
  public SettingParameterVo detail(String uid) {
    SettingParameterVo settingParameterVo = settingParameterDao.detail(uid);
    return settingParameterVo;
  }

  @Override
  public void delete(List<String> list) {
    settingParameterDao.delete(new QueryWrapper<SettingParameter>().in("uid", list));
    settingParameterConditionService.delete(list);
  }

  @Override
  public Page<SettingParameterListVo> list(SettingParamSearch settingParamSearch) {
    Page<SettingParameterListVo> page =
        new Page<>(settingParamSearch.getPageNum(), settingParamSearch.getPageSize());
    page = settingParameterDao.list(page, settingParamSearch);
    page.getRecords().stream()
        .forEach(
            settingParameterListVo -> {
              settingParameterListVo.setTypeName(
                  BusinessProcessEnum.ConditionType.getConditionType(
                      settingParameterListVo.getTypeName()));
            });
    return page;
  }

  @Override
  public Map<String, Object> selectList() {
    Map<String, Object> map = new HashMap<>(8);
    // 条件类型
    Map<String, String> monitorMap = BusinessProcessEnum.ConditionType.toMapData();
    map.put("monitorStatus", monitorMap);
    // 业务流程
    Map<String, String> dictDetailMap = new HashMap<>(32);
    List<TDictionariesDetailed> dictionariesDetailedList =
        dictionariesDetailedService.list(
            new QueryWrapper<TDictionariesDetailed>()
                .eq("classification_code", businessProcessCode));
    dictionariesDetailedList.stream()
        .forEach(
            dictionariesDetailed -> {
              dictDetailMap.put(
                  dictionariesDetailed.getId().toString(), dictionariesDetailed.getTypeName());
            });
    map.put("dictProcessType", dictDetailMap);
    // 操作符
    Map<Integer, String> operationMap = BusinessProcessEnum.OperationType.toMapData();
    map.put("operationType", operationMap);
    return map;
  }
}
