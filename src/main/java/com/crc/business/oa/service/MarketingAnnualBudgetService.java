package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.MarketingAnnualBudgetEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.MarketAnnualBudgetParam;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.result.MarketingAnnualBudgetVO;
import com.crc.business.oa.model.vo.result.OaCountVO;
import com.crc.business.oa.model.vo.result.OaInfoVoResult;
import com.crc.common.exception.ServiceException;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/1 10:23
 * @Version: 1.0
 */
public interface MarketingAnnualBudgetService extends IService<MarketingAnnualBudgetEntity> {
}
