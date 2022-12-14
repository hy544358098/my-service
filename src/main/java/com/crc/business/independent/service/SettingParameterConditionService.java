package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.SettingParameterCondition;

import java.util.List;

/**
 * @ClassName: SettingParameterConditionService
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 11:40
 * @Version: 1.0
 */
public interface SettingParameterConditionService extends IService<SettingParameterCondition> {
    /**
     * @param list
     * @Description TODO
     * @Date 2021/10/25 10:18
     * @Return void
     */
    void save(List<SettingParameterCondition> list);

    /**
     * @param list
     * @Description TODO
     * @Date 2021/10/25 10:18
     * @Return void
     */
    void delete(List<String> list);
}
