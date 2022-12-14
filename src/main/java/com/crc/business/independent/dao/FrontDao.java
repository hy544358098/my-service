package com.crc.business.independent.dao;

import com.crc.business.independent.model.vo.result.BusinessDataCount;
import com.crc.business.independent.model.vo.result.CityDateCount;
import com.crc.business.independent.model.vo.result.OwnerDataCount;
import com.crc.business.independent.model.vo.result.StatusCount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 首页接口
 * @Author: xiaoming
 * @Date: 2021/11/3 17:50
 * @Version: 1.0
 */
@Repository
public interface FrontDao {
    /**
     * @Return java.util.List<com.crc.business.independent.model.vo.result.CityDateCount>
     * @Description 按地区统计重点观察的每日数据和累计数据
     * @Date 2021/11/3 18:29
     */
    List<CityDateCount> cityDataCount();

    /**
     * @Description 按业务统计重点观察的每日数据和累计数据
     * @Date 2021/11/8 9:34
     * @Return java.util.List<com.crc.business.independent.model.vo.result.BusinessDataCount>
     */
    List<BusinessDataCount> businessDataCount();

    /**
     * @Description 统计我要观察的数据
     * @Date 2021/11/8 9:34
     * @Return com.crc.business.independent.model.vo.result.OwnerDataCount
     */
    OwnerDataCount ownerDataCount();

    /**
     * @Description 按状态统计所有数据
     * @Date 2021/12/17 15:24
     * @param
     * @Return {@link StatusCount}
     */
    StatusCount statusCount();
}
