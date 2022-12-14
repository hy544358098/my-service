package com.x.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.business.oa.dao.MarketingAnnualBudgetDao;
import com.x.business.oa.model.entity.MarketingAnnualBudgetEntity;
import com.x.business.oa.service.MarketingAnnualBudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MarketingAnnualBudgetServiceImpl
    extends ServiceImpl<MarketingAnnualBudgetDao, MarketingAnnualBudgetEntity>
    implements MarketingAnnualBudgetService {

}
