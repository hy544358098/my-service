package com.crc.business.oa.service;

import com.crc.business.oa.model.entity.AppendixEntity;
import com.crc.business.oa.model.entity.ApprovalEntity;
import com.crc.business.oa.model.pojo.OaRequestData;
import com.crc.business.oa.model.vo.result.OaTableInfo;
import com.crc.common.exception.ServiceException;

import java.util.List;

/**
 * @ClassName: OACommonService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 9:34
 * @Version: 1.0
 */
public interface CommonService {
    /**
     * @return java.lang.String
     * @Author xiaoming
     * @Description TODO
     * @Date 10:07 2021/10/19
     * @Param [roleNames]
     */
    String getRoleName(String roleNames);

}
