package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.MarketingApplyEntity;
import com.crc.business.oa.model.entity.MarketingFuseEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.MarketingFuseParam;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.result.*;
import com.crc.common.exception.ServiceException;


/**
 * 营销进度熔断重启
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-03 14:40:08
 */
public interface MarketingFuseService extends IService<MarketingFuseEntity> {
}

