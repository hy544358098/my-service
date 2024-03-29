package com.x.business.oa.dao;

import com.x.business.oa.model.entity.ApprovalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * OA审批流程表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Mapper
public interface ApprovalDao extends BaseMapper<ApprovalEntity> {
	
}
