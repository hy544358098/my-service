package com.x.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.x.business.oa.model.entity.MarketingFuseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 营销进度熔断重启
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-03 14:40:08
 */
@Mapper
public interface MarketingFuseDao extends BaseMapper<MarketingFuseEntity> {
}
