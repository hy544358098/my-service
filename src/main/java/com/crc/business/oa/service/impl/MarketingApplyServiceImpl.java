package com.crc.business.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.MarketingApplyDao;
import com.crc.business.oa.model.entity.MarketingApplyEntity;
import com.crc.business.oa.model.pojo.BudgetExecutionDetailsData;
import com.crc.business.oa.model.vo.request.MarketingApplyParam;
import com.crc.business.oa.model.vo.result.MarketingApplySubFormVo;
import com.crc.business.oa.model.vo.result.StatisticCommonVo;
import com.crc.business.oa.service.MarketingApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static com.crc.business.oa.service.impl.CommonServiceImpl.DEPT_MAP;
import static com.crc.common.constant.OaConstant.KM1;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service("tOaMarketingApplyService")
public class MarketingApplyServiceImpl extends ServiceImpl<MarketingApplyDao, MarketingApplyEntity>
    implements MarketingApplyService {

  private final MarketingApplyDao marketingApplyDao;


  @Override
  public List<StatisticCommonVo> statisticReport(MarketingApplyParam marketingApplyParam) {
    List<StatisticCommonVo> statisticCommonVoList = marketingApplyDao.statisticReport(marketingApplyParam);
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

  @Override
  public Page<MarketingApplySubFormVo> statisticList(MarketingApplyParam marketingApplyParam) {
    Page<MarketingApplySubFormVo> page =
            new Page<>(marketingApplyParam.getPageNum(), marketingApplyParam.getPageSize());
    page = marketingApplyDao.statisticList(page, marketingApplyParam);
    page.getRecords().stream()
        .forEach(
            marketingApplySubFormVo -> {
              String tableInfoStr = marketingApplySubFormVo.getTableInfo();
              List<BudgetExecutionDetailsData> tableInfoList = new ArrayList<>();
              try {
                tableInfoList = JSONObject.parseArray(tableInfoStr, BudgetExecutionDetailsData.class);
              } catch (Exception e) {
                log.error("parse json is error:{}", e.getMessage());
               return;
              }

              // 对一级科目去重处理
              Set<String> km1Set = new HashSet<>();
              AtomicReference<Double> thisAmount = new AtomicReference<>((double) 0);
              tableInfoList.stream()
                  .forEach(
                      tableInfo -> {
                        if (!StringUtils.equals(tableInfo.getKm1(), KM1)) {
                          km1Set.add(tableInfo.getKm1());
                          try {
                            thisAmount.updateAndGet(v -> v + new Double((tableInfo.getJe())));
                          } catch (Exception e) {
                            log.error("parse double is error:{}", e.getMessage());
                            return;
                          }
                        }
                      });
              marketingApplySubFormVo.setKm1(km1Set.toString());
              marketingApplySubFormVo.setThisAmount(thisAmount.get());
              marketingApplySubFormVo.setTableInfo("");
            });
    return page;
  }
}
