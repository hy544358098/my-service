package com.x.business.oa.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.x.business.oa.model.entity.LawCaseFilingApprovalEntity;
import com.x.business.oa.model.vo.request.LawCaseFilingApprovalParam;
import com.x.business.oa.model.vo.result.LawCaseStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 法律合规-案件处理方案审批
 *
 * @author xiaoming
 * @since 2021-10-13
 */
@Mapper
public interface LawCaseFilingApprovalDao extends BaseMapper<LawCaseFilingApprovalEntity> {

    /**
     * @Description 统计图表
     * @Date 2021/11/25 10:18
     * @param
     * @Return
     */
    List<LawCaseStatisticsVo> statistics(@Param("param") LawCaseFilingApprovalParam lawCaseFilingApprovalParam);
}
