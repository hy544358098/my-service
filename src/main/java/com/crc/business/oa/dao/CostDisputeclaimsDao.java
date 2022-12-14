package com.crc.business.oa.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.CostDisputeClaimsEntity;
import com.crc.business.oa.model.vo.request.CostDisputeClaimsParam;
import com.crc.business.oa.model.vo.result.CostStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成本合约—工程争议索赔
 *
 * @author xiaoming
 * @since 2021-10-13
 */
@Mapper
public interface CostDisputeclaimsDao extends BaseMapper<CostDisputeClaimsEntity> {

    /**
     * @Description 统计图表
     * @Date 2021/11/29 15:53
     * @param
     * @Return
     */
    List<CostStatisticsVo> statistics(@Param("param")CostDisputeClaimsParam costDisputeClaims);
}
