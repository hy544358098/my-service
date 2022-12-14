package com.crc.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.independent.model.entity.SettingParameterCondition;
import com.crc.business.independent.model.vo.result.SettingParamConditionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SettingParameterDao
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 11:36
 * @Version: 1.0
 */
@Repository
public interface SettingParameterConditionDao extends BaseMapper<SettingParameterCondition> {
    /**
     * @Description TODO
     * @Date 2021/10/22 16:15
     * @param typeCode 字段详情type_code字段查询参数列表
     * @Return java.util.List<com.crc.model.vo.result.SettingParameterVo>
     */
    List<SettingParamConditionVo> findByParamId(String typeCode);
}
