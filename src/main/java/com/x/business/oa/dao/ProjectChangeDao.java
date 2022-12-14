package com.x.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.x.business.oa.model.entity.ProjectChangeEntity;
import com.x.business.oa.model.vo.request.ProjectChangeParam;
import com.x.business.oa.model.vo.result.ProjectChangeStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OA工程变更表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-12 11:25:46
 */
@Mapper
public interface ProjectChangeDao extends BaseMapper<ProjectChangeEntity> {

    List<ProjectChangeStatisticsVo> statistics(@Param("param") ProjectChangeParam projectChangeParam);
}
