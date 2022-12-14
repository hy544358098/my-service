package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.oa.model.entity.CloudSupplierEntity;
import com.crc.business.oa.model.vo.request.CloudSupplierStatisticsParam;
import com.crc.business.oa.model.vo.result.CloudSupplierStatisticsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PurchaseCloudSupplierDao
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 11:28
 * @Version: 1.0
 */
@Repository
public interface CloudSupplierDao extends BaseMapper<CloudSupplierEntity> {

    /**
     * @Description 统计图表年
     * @Date 2021/11/26 10:18
     * @param
     * @Return
     */
    List<CloudSupplierStatisticsVo> statistics(@Param("param")CloudSupplierStatisticsParam param);

    /**
     * @Description 统计图表当日
     * @Date 2021/11/26 10:18
     * @param
     * @Return
     */
    List<CloudSupplierStatisticsVo> statisticsDay(@Param("param")CloudSupplierStatisticsParam param);

    /**
     * @Description 统计图表列表分页
     * @Date 2021/11/26 10:18
     * @param
     * @Return
     */
    Page<CloudSupplierStatisticsVo> statisticsPage(Page<CloudSupplierStatisticsVo> page,@Param("param")CloudSupplierStatisticsParam param);


}
