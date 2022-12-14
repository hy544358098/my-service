package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.MarketingAnnualBudgetDao;
import com.crc.business.oa.model.entity.MarketingAnnualBudgetEntity;
import com.crc.business.oa.service.MarketingAnnualBudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MarketingAnnualBudgetServiceImpl
    extends ServiceImpl<MarketingAnnualBudgetDao, MarketingAnnualBudgetEntity>
    implements MarketingAnnualBudgetService {

}
