package com.crc.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.independent.model.entity.SettingParameter;
import com.crc.business.independent.model.vo.request.SettingParamSearch;
import com.crc.business.independent.model.vo.result.SettingParameterListVo;
import com.crc.business.independent.model.vo.result.SettingParameterVo;
import org.apache.ibatis.annotations.Param;
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
public interface SettingParameterDao extends BaseMapper<SettingParameter> {
    /**
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.crc.business.Independent.model.vo.result.SettingParameterListVo>
     * @Author xiaoming
     * @Description TODO
     * @Date 14:02 2021/10/20
     * @Param [page, settingParamSearch]
     */
    Page<SettingParameterListVo> list(Page<SettingParameterListVo> page, SettingParamSearch settingParamSearch);

    /**
     * @return com.crc.business.Independent.model.vo.result.SettingParameterVo
     * @Author xiaoming
     * @Description TODO
     * @Date 15:15 2021/10/20
     * @Param [uid]
     */
    SettingParameterVo detail(String uid);

    /**
     * @Description 根据类型查询列表
     * @Date 2021/11/23 14:54
     * @param settingParam
     * @Return {@link List<SettingParameterListVo>}
    */
    List<SettingParameterListVo> getListByType(@Param("settingParam") SettingParamSearch settingParam);
}
