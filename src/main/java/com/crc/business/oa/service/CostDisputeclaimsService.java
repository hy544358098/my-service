package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.CostDisputeClaimsEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.request.CostDisputeClaimsParam;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.model.vo.result.CostDisputeClaimsVO;
import com.crc.business.oa.model.vo.result.OaInfoVoResult;
import com.crc.business.oa.model.vo.result.OaCountVO;
import com.crc.common.exception.ServiceException;

import java.util.Map;

/**
 * 成本合约—工程争议索赔
 *
 * @author xiaoming
 * @since 2021-10-13
 */
public interface CostDisputeclaimsService extends IService<CostDisputeClaimsEntity> {

    /**
     * @Description 统计图表
     * @Date 2021/11/29 15:13
     * @param
     * @Return {@link Object}
     */
    Map statistics(CostDisputeClaimsParam costDisputeClaims);
}

