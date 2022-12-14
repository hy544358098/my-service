package com.crc.business.oa.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.PurchaseSpecialEntity;
import com.crc.business.oa.model.vo.request.PurchaseSpecialParam;
import com.crc.business.oa.model.vo.result.PurchaseSpecialStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PurchaseSpecialDao extends BaseMapper<PurchaseSpecialEntity> {


     /**
      * @Description 报表统计
      * @Date 2021/10/22 13:46
      * @Return {@link List<PurchaseSpecialStatisticsVo>}
      */
     List<PurchaseSpecialStatisticsVo> statisticReport(@Param("param") PurchaseSpecialParam purchaseSpecialSeachVo);

     /**
      * @Description 报表统计
      * @Date 2021/10/22 13:46
      * @Return {@link List<PurchaseSpecialStatisticsVo>}
      */
     List<PurchaseSpecialStatisticsVo> statisticDayReport();

}
