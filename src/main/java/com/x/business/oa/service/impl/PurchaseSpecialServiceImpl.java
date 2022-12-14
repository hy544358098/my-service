package com.x.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.business.independent.service.UploadLinkAddressService;
import com.x.business.oa.dao.PurchaseSpecialDao;
import com.x.business.oa.model.entity.PurchaseSpecialEntity;
import com.x.business.oa.model.vo.request.PurchaseSpecialParam;
import com.x.business.oa.model.vo.result.PurchaseSpecialStatisticsVo;
import com.x.business.oa.service.CommonService;
import com.x.business.oa.service.OperationService;
import com.x.business.oa.service.PurchaseSpecialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.x.business.oa.service.impl.CommonServiceImpl.DEPT_MAP;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseSpecialServiceImpl
    extends ServiceImpl<PurchaseSpecialDao, PurchaseSpecialEntity>
    implements PurchaseSpecialService {

  private final PurchaseSpecialDao purchaseSpecialDao;
  private final CommonService commonService;
  private final UploadLinkAddressService uploadLinkAddressService;
  private final OperationService operationService;


  @Override
  public List<PurchaseSpecialStatisticsVo> statisticReport(PurchaseSpecialParam purchaseSpecialSeachVo) {
    List<PurchaseSpecialStatisticsVo> statisticsVoList = purchaseSpecialDao.statisticReport(purchaseSpecialSeachVo);
    List<PurchaseSpecialStatisticsVo> statisticsDayList = purchaseSpecialDao.statisticDayReport();
    List<PurchaseSpecialStatisticsVo> results = new ArrayList<>();
    DEPT_MAP.entrySet().stream()
        .forEach(
            entry -> {
              PurchaseSpecialStatisticsVo statisticsVo = new PurchaseSpecialStatisticsVo();
              statisticsVo.setCityName(entry.getValue());
              statisticsVoList.stream()
                  .forEach(
                      result -> {
                        if (StringUtils.equals(result.getCityName(), entry.getValue())) {
                          statisticsVo.setTodayTotal(result.getTodayTotal());
                          statisticsVo.setYearTotal(result.getYearTotal());
                        }
                      });
              results.add(statisticsVo);
            });

    results.stream()
            .forEach(
                    entry -> {
                      statisticsDayList.stream()
                              .forEach(
                                      result -> {
                                        if (StringUtils.equals(result.getCityName(), entry.getCityName())) {
                                          entry.setTodayTotal(result.getTodayTotal());
                                        }
                                      });
                    });
    return results;
  }
}
