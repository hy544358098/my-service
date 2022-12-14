package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.OrganizationLifeEntity;
import com.crc.business.oa.model.vo.request.OrganizationLifeParam;
import com.crc.business.oa.model.vo.result.LifeStatisticsVo;

/**
 * 党组织生活
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-29 16:07:12
 */
public interface OrganizationLifeService extends IService<OrganizationLifeEntity> {


  /**
   * @Description 统计图表
   * @Date 2021/12/1 9:58
   * @param
   * @Return {@link java.lang.Object}
   */
  LifeStatisticsVo statistics(OrganizationLifeParam param);
}
