package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.oa.model.vo.request.OperationParam;

/**
 * @author xiaoming
 * @date 2021-09-15 17:47:57
 */
public interface UploadLinkAddressService extends IService<UploadLinkAddress> {
    /**
     * @Author xiaoming
     * @Description TODO 
     * @Date 10:28 2021/10/21
     * @Param [purchaseOperationParam]
     * @return void
     */
    void savaUploadFile(OperationParam purchaseOperationParam);
}
