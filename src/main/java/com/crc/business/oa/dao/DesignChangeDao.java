package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.DesignChangeEntity;
import com.crc.business.oa.model.vo.request.DesignChangeParam;
import com.crc.business.oa.model.vo.result.StatisticCommonVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OA设计变更表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-15 14:07:48
 */
@Mapper
public interface DesignChangeDao extends BaseMapper<DesignChangeEntity> {


  /**
   * @Description 报表统计
   * @Date 2021/10/22 13:46
   * @Return {@link List<StatisticCommonVo>}
   */
  List<StatisticCommonVo> statisticReport(@Param("param")DesignChangeParam designChangeParam);
}
