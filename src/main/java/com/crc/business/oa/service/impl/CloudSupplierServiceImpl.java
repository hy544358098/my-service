package com.crc.business.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.CloudSupplierDao;
import com.crc.business.oa.model.entity.CloudSupplierEntity;
import com.crc.business.oa.model.vo.request.CloudSupplierStatisticsParam;
import com.crc.business.oa.model.vo.result.CloudSupplierStatisticsVo;
import com.crc.business.oa.service.CloudSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CloudSupplierServiceImpl extends ServiceImpl<CloudSupplierDao, CloudSupplierEntity>
    implements CloudSupplierService {
  @Autowired private CloudSupplierDao cloudSupplierDao;

  @Override
  public Map statistics(CloudSupplierStatisticsParam param) {
    List<CloudSupplierStatisticsVo> statisticsYear = cloudSupplierDao.statistics(param);
    List<CloudSupplierStatisticsVo> statisticsDay = cloudSupplierDao.statisticsDay(param);
    Map<String, Map<String, Integer>> map = new HashMap<>();
    CommonServiceImpl.DEPT_MAP.keySet().stream()
        .forEach(
            set -> {
              if (!map.containsKey(CommonServiceImpl.DEPT_MAP.get(set))) {
                Map<String, Integer> mapNew = new HashMap<>();
                mapNew.put("Year", 0);
                mapNew.put("Day", 0);
                map.put(CommonServiceImpl.DEPT_MAP.get(set), mapNew);
              }
            });
    Map<String, Map<String, Integer>> mapYear = calculate(statisticsYear, "Year", map);
    Map<String, Map<String, Integer>> mapDay = calculate(statisticsDay, "Day", mapYear);
    return mapDay;
  }

  public Map<String, Map<String, Integer>> calculate(
      List<CloudSupplierStatisticsVo> statistics,
      String dayOrYear,
      Map<String, Map<String, Integer>> map) {
    statistics.stream()
        .forEach(
            vo -> {
              String tableInfo = vo.getTableInfo();
              List list = JSONObject.parseArray(tableInfo);
              // 映射城市公司并计算和
              if (map.containsKey(vo.getStatisticsCityName()) && list != null) {
                Map<String, Integer> mapNew = map.get(vo.getStatisticsCityName());
                mapNew.put(
                    dayOrYear,
                    map.get(vo.getStatisticsCityName()).get(dayOrYear) + list.size() - 1);
                map.put(vo.getStatisticsCityName(), mapNew);
              } else {
                if (StringUtils.isNotEmpty(vo.getStatisticsCityName()) && list != null) {
                  Map<String, Integer> mapNew = null;
                    if (CollectionUtil.isNotEmpty(map.get(vo.getStatisticsCityName()))) {
                        mapNew.put(
                                dayOrYear,
                                map.get(vo.getStatisticsCityName()).get(dayOrYear) + list.size() - 1);
                        map.put(vo.getStatisticsCityName(), mapNew);
                    }
                }
              }
            });
    return map;
  }

  @Override
  public Page<CloudSupplierStatisticsVo> statisticsList(CloudSupplierStatisticsParam param) {
    Page<CloudSupplierStatisticsVo> page = new Page<>(param.getPageNum(), param.getPageSize());
    page = cloudSupplierDao.statisticsPage(page, param);
    return page;
  }
}
