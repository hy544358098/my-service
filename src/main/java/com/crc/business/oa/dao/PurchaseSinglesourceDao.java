package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.PurchaseSingleSourceEntity;
import com.crc.business.oa.model.vo.request.PurchaseSinglesourceParam;
import com.crc.business.oa.model.vo.result.PurchaseSinglesourceStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 采购方式变更及单一来源定标申请表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Mapper
public interface PurchaseSinglesourceDao extends BaseMapper<PurchaseSingleSourceEntity> {



     /**
      * @Description 统计图表年
      * @Date 2021/11/25 10:18
      * @param
      * @Return
      */
     List<PurchaseSinglesourceStatisticsVo> statisticsYear(@Param("param") PurchaseSinglesourceParam singlesourceSeachVo);

     /**
      * @Description 统计图表日
      * @Date 2021/11/25 10:18
      * @param
      * @Return
      */
     List<PurchaseSinglesourceStatisticsVo> statisticsDay();
}
