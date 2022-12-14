package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.OaOperateEntity;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.common.exception.ServiceException;

/**
 * @ClassName: OperationService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/13 11:43
 * @Version: 1.0
 */
public interface OperationService extends IService<OaOperateEntity> {
    /**
     * @return void
     * @Author xiaoming
     * @Description 操作人操作数据保存
     * @Date 10:31 2021/10/21
     * @Param [purchaseOperationParam]
     */
    void saveOperationData(OperationParam purchaseOperationParam)throws ServiceException;

    /**
     * @return void
     * @Author xiaoming
     * @Description 系统转预警存储造作记录
     * @Date 15:21 2022/01/12
     * @Param [purchaseOperationParam]
     */
    void systemOperation(OaOperateEntity purchaseOperationParam)throws ServiceException;
}
