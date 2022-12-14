package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.SettingParameter;
import com.crc.business.independent.model.vo.request.SettingParamPO;
import com.crc.business.independent.model.vo.request.SettingParamSearch;
import com.crc.business.independent.model.vo.result.SettingParameterListVo;
import com.crc.business.independent.model.vo.result.SettingParameterVo;
import com.crc.common.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SettingParameterService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 11:39
 * @Version: 1.0
 */
public interface SettingParameterService extends IService<SettingParameter> {
    /**
     * @return void
     * @Author xiaoming
     * @Description TODO
     * @Date 15:22 2021/10/19
     * @Param [settingParam]
     */
    void save(SettingParamPO settingParam,String userId) throws ServiceException;

    /**
     * @return com.crc.business.Independent.model.vo.result.SettingParameterVo
     * @Author xiaoming
     * @Description TODO
     * @Date 15:22 2021/10/19
     * @Param [uid]
     */
    SettingParameterVo detail(String uid);

    /**
     * @return void
     * @Author xiaoming
     * @Description TODO
     * @Date 15:22 2021/10/19
     * @Param [list]
     */
    void delete(List<String> list);

   /**
    * @Author xiaoming
    * @Description TODO 
    * @Date 17:32 2021/10/19
    * @Param [settingParamSearch]
    * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.crc.business.Independent.model.entity.SettingParameter>
    */
    Page<SettingParameterListVo> list(SettingParamSearch settingParamSearch);
    
    /**
     * @Author xiaoming
     * @Description TODO 
     * @Date 9:44 2021/10/20
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String,Object> selectList();
}
