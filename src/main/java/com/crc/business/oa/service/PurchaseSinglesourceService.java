package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.PurchaseSingleSourceEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.result.OaInfoVoResult;
import com.crc.business.oa.model.vo.result.ProjectSinglesourceVo;
import com.crc.business.oa.model.vo.result.OaCountVO;
import com.crc.business.oa.model.vo.request.PurchaseSinglesourceParam;
import com.crc.common.exception.ServiceException;

import java.util.Map;


/**
 * @Description 采购方式变更及单一来源定标申请
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
public interface PurchaseSinglesourceService extends IService<PurchaseSingleSourceEntity> {

     /**
      * @Description 统计图表
      * @Date 2021/11/25 10:16
      * @param
      * @Return {@link Object}
      */
     Map statistics(PurchaseSinglesourceParam singlesourceSeachVo);
}

