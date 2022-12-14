package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.PurchaseShortlistedEntity;
import com.crc.business.oa.model.vo.request.PurchaseShortListedParam;
import com.crc.business.oa.model.vo.result.PurchaseShortListedStatisticsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PurchaseShortlistedDao
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/11 14:03
 * @Version: 1.0
 */
@Repository
public interface PurchaseShortlistedDao extends BaseMapper<PurchaseShortlistedEntity> {

    /**
     * @Description 报表统计
     * @Date 2021/10/22 13:46
     * @Return {@link List <PurchaseShortListedStatisticsVo>}
     */
    List<PurchaseShortListedStatisticsVo> statisticReport(@Param("param") PurchaseShortListedParam param);
}
