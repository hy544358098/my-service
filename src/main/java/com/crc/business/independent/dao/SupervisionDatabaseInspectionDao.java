package com.crc.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.independent.model.entity.SupervisionDatabaseInspection;
import com.crc.business.independent.model.vo.result.DatabaseStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2021-09-18 11:31:02
 */
@Mapper
public interface SupervisionDatabaseInspectionDao
    extends BaseMapper<SupervisionDatabaseInspection> {
    /**
     * @Description 统计图表一
     * @Date 2021/11/23 14:21
     * @param date
     * @Return {@link Map}
     */
    List<DatabaseStatisticsVo> statisticsOne(@Param("date") String date);

    /**
     * @Description 统计图表二
     * @Date 2021/11/23 15:08
     * @Return {@link List<  DatabaseStatisticsVo >}
     */
    List<DatabaseStatisticsVo> statisticsTwo();

    /**
     * @Description 统计图表三
     * @Date 2021/11/23 15:08
     * @param date
     * @Return {@link List<  DatabaseStatisticsVo >}
     */
    List<DatabaseStatisticsVo> statisticsThree(@Param("date") String date);

    /**
     * @Description 统计图表四
     * @Date 2021/11/23 15:08
     * @Return {@link List<  DatabaseStatisticsVo >}
     */
    List<DatabaseStatisticsVo> statisticsFour();
}
