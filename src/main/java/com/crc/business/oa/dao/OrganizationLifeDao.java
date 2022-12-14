package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.OrganizationLifeEntity;
import com.crc.business.oa.model.vo.request.OrganizationLifeParam;
import com.crc.business.oa.model.vo.result.LifeStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 党组织生活
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-29 16:07:12
 */
@Mapper
public interface OrganizationLifeDao extends BaseMapper<OrganizationLifeEntity> {

    /**
     * @Description 数据统计
     * @Date 2021/12/10 10:57
     * @param
     * @Return
     */
    LifeStatisticsVo statistics(@Param("param") OrganizationLifeParam param);
}
