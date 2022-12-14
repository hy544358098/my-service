package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.PurchaseSinglesourceDao;
import com.crc.business.oa.model.entity.PurchaseSingleSourceEntity;
import com.crc.business.oa.model.vo.request.PurchaseSinglesourceParam;
import com.crc.business.oa.model.vo.result.PurchaseSinglesourceStatisticsVo;
import com.crc.business.oa.service.PurchaseSinglesourceService;
import com.crc.common.utils.PatternUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service("tOaPurchaseSinglesourceService")
public class PurchaseSinglesourceServiceImpl
        extends ServiceImpl<PurchaseSinglesourceDao, PurchaseSingleSourceEntity>
        implements PurchaseSinglesourceService {
    private final PurchaseSinglesourceDao purchaseSinglesourceDao;

    private final String ESTIMATEDAMOUNT = "estimatedAmount";
    private final String TOTAL = "total";


    @Override
    public Map statistics(PurchaseSinglesourceParam singlesourceSeachVo) {
        Map<String, Map<String, Object>> mapYear = new HashMap<>();
        Map<String, Map<String, Object>> mapDay = new HashMap<>();
        Map data = new HashMap<>();
        List<PurchaseSinglesourceStatisticsVo> voYear = purchaseSinglesourceDao.statisticsYear(singlesourceSeachVo);
        List<PurchaseSinglesourceStatisticsVo> voDay = purchaseSinglesourceDao.statisticsDay();
        //计算当年
        voYear.stream().forEach(vo -> {
            if (null != vo.getStatisticsCityNameYear()) {
                Map<String, Object> mapNew = new HashMap<>();
                // 映射城市公司并计算和
                if (mapYear.get(vo.getStatisticsCityNameYear()) == null) {
                    String money = PatternUtils.money(vo.getEstimatedAmountYear().replace(",", ""));
                    mapNew.put(ESTIMATEDAMOUNT, money);
                    mapNew.put(TOTAL, 1);
                } else {
                    mapNew = mapYear.get(vo.getStatisticsCityNameYear());
                    if (StringUtils.isNotEmpty(vo.getEstimatedAmountYear())) {
                        String money =
                                PatternUtils.money(
                                        vo.getEstimatedAmountYear().replace(",", "") , (String) mapNew.get(ESTIMATEDAMOUNT));
                        mapNew.put(ESTIMATEDAMOUNT, money);
                        Integer total = (Integer) mapNew.get(TOTAL) + 1;
                        mapNew.put(TOTAL, total);
                    }
                }
                mapYear.put(vo.getStatisticsCityNameYear(), mapNew);
            }
        });
        //补充没有值的城市
        CommonServiceImpl.DEPT_MAP.keySet().stream().forEach(set -> {
            boolean flag = true;
            for (Map.Entry<String, Map<String, Object>> entry : mapYear.entrySet()) {
                if (StringUtils.equals(CommonServiceImpl.DEPT_MAP.get(set), entry.getKey())) {
                    flag = false;
                }
            }
            if (flag) {
                Map<String, Object> mapNew = new HashMap<>();
                mapNew.put(ESTIMATEDAMOUNT, "0");
                mapNew.put(TOTAL, "0");
                mapYear.put(CommonServiceImpl.DEPT_MAP.get(set), mapNew);
            }
        });
        data.put("Year", mapYear);

        //计算当日
        voDay.stream().forEach(vo -> {
            if (null != vo.getStatisticsCityNameDay()) {
                Map<String, Object> mapNew = new HashMap<>();
                // 映射城市公司并计算和
                if (mapDay.get(vo.getStatisticsCityNameDay()) == null) {
                    String money = PatternUtils.money(vo.getEstimatedAmountDay().replace(",", ""));
                    mapNew.put(ESTIMATEDAMOUNT, money);
                    mapNew.put(TOTAL, 1);
                } else {
                    mapNew = mapDay.get(vo.getStatisticsCityNameDay());
                    if (StringUtils.isNotEmpty(vo.getEstimatedAmountDay())) {
                        String money =
                                PatternUtils.money(
                                        vo.getEstimatedAmountDay().replace(",", "") , (String)mapNew.get(ESTIMATEDAMOUNT));
                        mapNew.put(ESTIMATEDAMOUNT, money);
                        Integer total = (Integer) mapNew.get(TOTAL) + 1;
                        mapNew.put(TOTAL, total);
                    }
                }
                mapDay.put(vo.getStatisticsCityNameDay(), mapNew);
            }
        });
        //补充没有值的城市
        CommonServiceImpl.DEPT_MAP.keySet().stream().forEach(set -> {
            boolean flag = true;
            for (Map.Entry<String, Map<String, Object>> entry : mapDay.entrySet()) {
                if (StringUtils.equals(CommonServiceImpl.DEPT_MAP.get(set), entry.getKey())) {
                    flag = false;
                }
            }
            if (flag) {
                Map<String, Object> mapNew = new HashMap<>();
                mapNew.put(ESTIMATEDAMOUNT, "0");
                mapNew.put(TOTAL, "0");
                mapDay.put(CommonServiceImpl.DEPT_MAP.get(set), mapNew);
            }
        });
        data.put("Day", mapDay);
        return data;
    }
}
