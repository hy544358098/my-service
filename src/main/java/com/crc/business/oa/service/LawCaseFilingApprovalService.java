package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.LawCaseFilingApprovalEntity;
import com.crc.business.oa.model.vo.request.LawCaseFilingApprovalParam;

import java.util.List;

/**
 * 法律合规-案件处理方案审批
 *
 * @author xiaoming
 * @since 2021-10-13
 */
public interface LawCaseFilingApprovalService extends IService<LawCaseFilingApprovalEntity> {


    /**
     * @Description 统计图表
     * @Date 2021/11/30 14:26
     * @param
     * @Return {@link Object}
     */
    List statistics(LawCaseFilingApprovalParam lawCaseFilingApprovalParam);
}

