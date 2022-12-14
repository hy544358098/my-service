package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.PurchaseSpecialEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.request.PurchaseSpecialParam;
import com.crc.business.oa.model.vo.result.*;
import com.crc.common.exception.ServiceException;

import java.util.List;

/**
 * @Description 特殊资源比选内容信息
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
public interface PurchaseSpecialService extends IService<PurchaseSpecialEntity> {

    /**
     * @Description 报表统计
     * @Date 2021/10/22 13:46
     * @Return {@link List<PurchaseSpecialStatisticsVo>}
     */
    List<PurchaseSpecialStatisticsVo> statisticReport(PurchaseSpecialParam purchaseSpecialSeachVo);
}

