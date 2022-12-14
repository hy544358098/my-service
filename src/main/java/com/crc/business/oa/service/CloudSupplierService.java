package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.CloudSupplierEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.CloudSupplierParam;
import com.crc.business.oa.model.vo.request.CloudSupplierStatisticsParam;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.result.CloudSupplierStatisticsVo;
import com.crc.business.oa.model.vo.result.CloudSupplierVO;
import com.crc.business.oa.model.vo.result.OaInfoVoResult;
import com.crc.business.oa.model.vo.result.OaCountVO;
import com.crc.common.exception.ServiceException;

import java.util.Map;

/**
 * @ClassName: PurchaseCloudSupplierService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/12 11:03
 * @Version: 1.0
 */
public interface CloudSupplierService extends IService<CloudSupplierEntity> {

    /**
     * @Description 统计图表
     * @Date 2021/11/26 14:13
     * @param
     * @Return {@link Object}
     */
    Map statistics(CloudSupplierStatisticsParam param);

    /**
     * @Description 统计列表
     * @Date 2021/11/29 11:21
     * @param
     * @Return {@link Map}
     */
    Page<CloudSupplierStatisticsVo> statisticsList(CloudSupplierStatisticsParam param);
}
