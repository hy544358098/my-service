package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.MarketingFuseDao;
import com.crc.business.oa.model.entity.MarketingFuseEntity;
import com.crc.business.oa.service.MarketingFuseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tOaMarketingFuseService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MarketingFuseServiceImpl extends ServiceImpl<MarketingFuseDao, MarketingFuseEntity>
    implements MarketingFuseService {

}
