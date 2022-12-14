package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.OrganizationLifeDao;
import com.crc.business.oa.model.entity.OrganizationLifeEntity;
import com.crc.business.oa.model.vo.request.OrganizationLifeParam;
import com.crc.business.oa.model.vo.result.LifeStatisticsVo;
import com.crc.business.oa.service.OrganizationLifeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service("tOaOrganizationLifeService")
public class OrganizationLifeServiceImpl
    extends ServiceImpl<OrganizationLifeDao, OrganizationLifeEntity>
    implements OrganizationLifeService {

  private final OrganizationLifeDao organizationLifeDao;


  @Override
  public LifeStatisticsVo statistics(OrganizationLifeParam param) {
    LifeStatisticsVo statistics = organizationLifeDao.statistics(param);
    return statistics;
  }


}
