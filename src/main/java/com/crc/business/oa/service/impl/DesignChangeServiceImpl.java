package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.DesignChangeDao;
import com.crc.business.oa.model.entity.DesignChangeEntity;
import com.crc.business.oa.model.vo.request.DesignChangeParam;
import com.crc.business.oa.model.vo.result.StatisticCommonVo;
import com.crc.business.oa.service.DesignChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.crc.business.oa.service.impl.CommonServiceImpl.DEPT_MAP;

@Slf4j
@Service("tOaDesignChangeService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DesignChangeServiceImpl extends ServiceImpl<DesignChangeDao, DesignChangeEntity>
    implements DesignChangeService {
  private final DesignChangeDao designChangeDao;

  @Override
  public List<StatisticCommonVo> statisticReport(DesignChangeParam designChangeParam) {
    List<StatisticCommonVo> statisticCommonVoList = designChangeDao.statisticReport(designChangeParam);
    List<StatisticCommonVo> results = new ArrayList<>();
    DEPT_MAP.entrySet().stream()
        .forEach(
            entry -> {
              StatisticCommonVo statisticCommonVo = new StatisticCommonVo();
              statisticCommonVo.setCityName(entry.getValue());
              statisticCommonVoList.stream()
                  .forEach(
                      result -> {
                        if (StringUtils.equals(result.getCityName(), entry.getValue())) {
                          statisticCommonVo.setTotal(result.getTotal());
                        }
                      });
              results.add(statisticCommonVo);
            });
    return results;
  }
}
