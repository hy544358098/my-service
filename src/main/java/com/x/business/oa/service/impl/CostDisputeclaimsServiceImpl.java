package com.x.business.oa.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.business.oa.dao.CostDisputeclaimsDao;
import com.x.business.oa.model.entity.CostDisputeClaimsEntity;
import com.x.business.oa.model.vo.request.CostDisputeClaimsParam;
import com.x.business.oa.model.vo.result.CostStatisticsVo;
import com.x.business.oa.service.CostDisputeclaimsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tOaCostDisputeclaimsService")
@Slf4j
public class CostDisputeclaimsServiceImpl
    extends ServiceImpl<CostDisputeclaimsDao, CostDisputeClaimsEntity>
    implements CostDisputeclaimsService {

  @Autowired private CostDisputeclaimsDao costDisputeclaimsDao;

  @Override
  public Map statistics(CostDisputeClaimsParam costDisputeClaims) {
    List<CostStatisticsVo> statistics = costDisputeclaimsDao.statistics(costDisputeClaims);
    Map<String, String> map = new HashMap<>();
    statistics.stream().forEach(vo->{
      map.put(vo.getStatisticsCityName(),vo.getBothAgreedAmount());
    });
    CommonServiceImpl.DEPT_MAP.keySet().stream()
            .forEach(set -> {
                      boolean flag = true;
                      for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (StringUtils.equals(CommonServiceImpl.DEPT_MAP.get(set), entry.getKey())) {
                          flag = false;
                        }
                      }
                      if (flag) {
                        map.put(CommonServiceImpl.DEPT_MAP.get(set), "0");
                      }
                    });
    return map;
  }
}
