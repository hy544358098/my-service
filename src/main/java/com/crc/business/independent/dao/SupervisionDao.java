package com.crc.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.independent.model.entity.Supervision;
import com.crc.business.independent.model.vo.result.MySupervisionCountVo;
import com.crc.business.independent.model.vo.request.SupervisionParam;
import com.crc.business.independent.model.vo.result.SupervisionStatisticsVo;
import com.crc.business.independent.model.vo.result.SupervisionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
@Mapper
public interface SupervisionDao extends BaseMapper<Supervision> {

  /**
   * 列表分页查询
   *
   * @return
   */
  Page<SupervisionVo> list(Page<SupervisionVo> page, SupervisionParam param);

  /**
   * excel列表分页查询
   *
   * @return
   */
  List<SupervisionVo> excelList(SupervisionParam param);

  /**
   * @return PurchaseShortListCountVO
   * @Description 数据状态统计
   * @Date 15:40 2021/10/13
   * @Param [costDisputeClaims]
   */
  MySupervisionCountVo statisticalData(SupervisionParam param);


  /**
   * @Description 统计图表一
   * @Date 2021/11/24 10:43
   * @param
   * @Return {@link List< SupervisionStatisticsVo>}
   */
  List<SupervisionStatisticsVo> statisticsOne();

  /**
   * @Description 统计图表二
   * @Date 2021/11/24 10:43
   * @param
   * @Return {@link List< SupervisionStatisticsVo>}
   */
  List<SupervisionStatisticsVo> statisticsTwo();

  /**
   * @Description 统计图表三
   * @Date 2021/11/24 10:43
   * @param
   * @Return {@link List< SupervisionStatisticsVo>}
   */
  List<SupervisionStatisticsVo> statisticsThree();

  /**
   * @Description 置顶
   * @Date 2022/1/26 10:16
   * @param id
   * @param topType
   * @Return
   */
  void top(@Param("id") String id, @Param("topType") Integer topType);
}
