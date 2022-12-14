package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.common.constant.OaConstant;
import com.crc.common.enums.oa.OaCommonStatusEnum;
import com.crc.business.independent.dao.UploadLinkAddressDao;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.independent.service.UploadLinkAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoming
 */
@Service("uploadLinkAddressService")
public class UploadLinkAddressServiceImpl
        extends ServiceImpl<UploadLinkAddressDao, UploadLinkAddress>
        implements UploadLinkAddressService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savaUploadFile(OperationParam purchaseOperationParam) {
        if (OaCommonStatusEnum.AlertNode.STATUS_FEEDBACK
                .getCode()
                .equals(purchaseOperationParam.getOperationName())
                && null != purchaseOperationParam.getFileId()
                && purchaseOperationParam.getFileId().size() > 0) {
            List<UploadLinkAddress> uploadLinkAddressList =
                    list(new QueryWrapper<UploadLinkAddress>().in("id", purchaseOperationParam.getFileId()));
            uploadLinkAddressList.stream()
                    .forEach(
                            uploadLinkAddress -> {
                                uploadLinkAddress.setBusinessType(OaConstant.OA_FOCUS_SUPERVISE);
                                uploadLinkAddress.setUid(purchaseOperationParam.getSupervisionId());
                            });
            updateBatchById(uploadLinkAddressList);
        }
    }
}
