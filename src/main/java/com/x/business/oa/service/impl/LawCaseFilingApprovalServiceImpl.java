package com.x.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.business.oa.dao.LawCaseFilingApprovalDao;
import com.x.business.oa.model.entity.LawCaseFilingApprovalEntity;
import com.x.business.oa.model.vo.request.LawCaseFilingApprovalParam;
import com.x.business.oa.model.vo.result.LawCaseStatisticsVo;
import com.x.business.oa.service.LawCaseFilingApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LawCaseFilingApprovalServiceImpl
    extends ServiceImpl<LawCaseFilingApprovalDao, LawCaseFilingApprovalEntity>
    implements LawCaseFilingApprovalService {

  @Autowired private LawCaseFilingApprovalDao lawCaseFilingApprovalDao;


  @Override
  public List statistics(LawCaseFilingApprovalParam lawCaseFilingApprovalParam) {
    List<LawCaseStatisticsVo> statistics = lawCaseFilingApprovalDao.statistics(lawCaseFilingApprovalParam);
    CommonServiceImpl.DEPT_MAP.keySet().stream()
        .forEach(
            set -> {
              boolean flag = true;
              for (LawCaseStatisticsVo list : statistics) {
                if (StringUtils.equals(
                    CommonServiceImpl.DEPT_MAP.get(set), list.getStatisticsCityName())) {
                  flag = false;
                }
              }
              if (flag) {
                LawCaseStatisticsVo lawCaseStatisticsVo = new LawCaseStatisticsVo();
                lawCaseStatisticsVo.setStatisticsCityName(CommonServiceImpl.DEPT_MAP.get(set));
                lawCaseStatisticsVo.setTotal(0);
                statistics.add(lawCaseStatisticsVo);
              }
            });
    return statistics;
  }
}
