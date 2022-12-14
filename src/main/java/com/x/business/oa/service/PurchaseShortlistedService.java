package com.x.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.x.business.oa.model.entity.PurchaseShortlistedEntity;
import com.x.business.oa.model.vo.request.PurchaseShortListedParam;

import java.util.List;

/**
 * @ClassName: PurchaseShortlistedService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/11 14:02
 * @Version: 1.0
 */
public interface PurchaseShortlistedService extends IService<PurchaseShortlistedEntity> {
    /**
     * @Description 报表统计
     * @Date 2021/10/22 13:46
     * @Return {@link List <PurchaseShortListedStatisticsVo>}
     */
    Page statisticReport(PurchaseShortListedParam shortList);
}
