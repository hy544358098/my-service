package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.oa.model.entity.MarketingApplyEntity;
import com.crc.business.oa.model.vo.request.MarketingApplyParam;
import com.crc.business.oa.model.vo.result.MarketingApplySubFormVo;
import com.crc.business.oa.model.vo.result.StatisticCommonVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 营销预算执行申请
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-28 10:25:39
 */
@Mapper
public interface MarketingApplyDao extends BaseMapper<MarketingApplyEntity> {


  /**
   * @Description 报表统计
   * @Date 2021/10/22 13:46
   * @Return {@link List<StatisticCommonVo>}
   */
  List<StatisticCommonVo> statisticReport(@Param("param") MarketingApplyParam marketingApplyParam);

  /**
   * @Description 子表统计列表
   * @Date 2021/10/22 13:46
   * @Return {@link Page<MarketingApplySubFormVo>}
   */
  Page<MarketingApplySubFormVo> statisticList(Page<MarketingApplySubFormVo> page,@Param("subParam") MarketingApplyParam marketingApplyParam);
}
