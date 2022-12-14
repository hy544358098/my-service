package com.x.business.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.x.business.oa.model.entity.DesignChangeEntity;
import com.x.business.oa.model.vo.request.DesignChangeParam;
import com.x.business.oa.model.vo.result.StatisticCommonVo;

import java.util.List;


/**
 * @Description OA设计变更审批
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
public interface DesignChangeService extends IService<DesignChangeEntity> {



    /**
     * @Description 报表统计
     * @Date 2021/10/22 13:46
     * @Return {@link List<StatisticCommonVo>}
     */
    List<StatisticCommonVo> statisticReport(DesignChangeParam designChangeParam);
}

