package com.crc.business.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.PurchaseShortlistedDao;
import com.crc.business.oa.model.entity.PurchaseShortlistedEntity;
import com.crc.business.oa.model.pojo.QualificationFinalistResultsData;
import com.crc.business.oa.model.vo.request.PurchaseShortListedParam;
import com.crc.business.oa.model.vo.result.PurchaseShortListedStatisticsVo;
import com.crc.business.oa.service.PurchaseShortlistedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.crc.business.oa.service.impl.CommonServiceImpl.DEPT_MAP;
import static com.crc.common.constant.OaConstant.*;

@Service
@Slf4j
public class PurchasePurchaseShortlistedServiceImpl
    extends ServiceImpl<PurchaseShortlistedDao, PurchaseShortlistedEntity>
    implements PurchaseShortlistedService {

  @Autowired private PurchaseShortlistedDao shortlistedDao;
    private final String ALL = "全量";
    private final String THEN = "当年";

    @Override
    public Page statisticReport(PurchaseShortListedParam param) {
        List<PurchaseShortListedStatisticsVo> statisticVoList = shortlistedDao.statisticReport(param);
        Map<String, Map<String, Integer>> map = new HashMap();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        for (PurchaseShortListedStatisticsVo result : statisticVoList) {
            String tableInfo = result.getTableInfo();
            List<QualificationFinalistResultsData> tableInfoList = new ArrayList<>();
            try {
                tableInfoList = JSONObject.parseArray(tableInfo, QualificationFinalistResultsData.class);
            } catch (Exception e) {
                log.error("parse json is error:{}", e.getMessage());
                continue;
            }
            for (QualificationFinalistResultsData list : tableInfoList) {
                // 搜索字段筛选
                if (StringUtils.isEmpty(param.getCompanyName())
                        || StringUtils.contains(list.getName(), param.getCompanyName())) {
                    // 判断是否入围
                    if (StringUtils.equals(list.getSelected(), SELECTED)) {
                        // 判断此公司在返回值map中是否已有
                        if (CollectionUtil.isEmpty(map.get(list.getName()))) {
                            Map<String, Integer> company = nameMap(list.getName());
                            // 判断是否为当年
                            if (StringUtils.contains(result.getApplyDate(), year + "")) {
                                company.put(result.getCityName() + THEN, 1);
                            } else {
                                company.put(result.getCityName() + ALL, 1);
                            }
                            map.put(list.getName(), company);
                        } else {
                            Map<String, Integer> map1 = map.get(list.getName());
                            // 判断是否为当年
                            if (StringUtils.contains(result.getApplyDate(), year + "")) {
                                map1.put(result.getCityName() + THEN, map1.get(result.getCityName() + THEN) + 1);
                            } else {
                                map1.put(result.getCityName() + ALL, map1.get(result.getCityName() + ALL) + 1);
                            }
                        }
                    }
                }
            }
        }
        // 转为list分页
        List<Map<String, Map<String, Integer>>> list = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> m : map.entrySet()) {
            Map<String, Map<String, Integer>> map1 = new HashMap<>();
            map1.put(m.getKey(), m.getValue());
            list.add(map1);
        }
        if (list.size() == 0) {
            return new Page();
        }
        return getPage(param.getPageNum(), param.getPageSize(), list);
    }

    // 初始公司名称对应map
    public Map<String, Integer> nameMap(String name) {
        Map<String, Integer> map = new HashMap<>();
        DEPT_MAP.entrySet().stream()
                .forEach(
                        cityEntry -> {
                            if (StringUtils.equalsAny(cityEntry.getKey(), CITY_NAME_DONGANH, CITY_NAME_XIAN_HUIZHAN)){
                                return;
                            }
                            map.put(cityEntry.getValue() + THEN, 0);
                            map.put(cityEntry.getValue() + ALL, 0);
                        });
        return map;
    }

    // 分页
    public Page getPage(Integer pageNum, Integer pageSize, List list) {
        Page mapPage = new Page<>(pageNum, pageSize);
        // 页数
        Integer pageCount = 0;
        if (list.size() % pageSize == 0) {
            pageCount = list.size() / pageSize;
        } else {
            pageCount = list.size() / pageSize + 1;
        }
        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;
        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = list.size();
        }
        List pageList = list.subList(fromIndex, toIndex);
        mapPage.setTotal(list.size());
        mapPage.setRecords(pageList);
        return mapPage;
    }
}
