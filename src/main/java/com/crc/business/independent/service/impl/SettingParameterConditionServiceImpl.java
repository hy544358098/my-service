package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.SettingParameterConditionDao;
import com.crc.business.independent.model.entity.SettingParameterCondition;
import com.crc.business.independent.service.SettingParameterConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: SettingParameterConditionServiceImpl
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/19 14:07
 * @Version: 1.0
 */
@Service
public class SettingParameterConditionServiceImpl extends ServiceImpl<SettingParameterConditionDao, SettingParameterCondition> implements SettingParameterConditionService {
    @Autowired
    private SettingParameterConditionDao settingParameterConditionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<SettingParameterCondition> list) {
        saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> list) {
        settingParameterConditionDao.delete(new QueryWrapper<SettingParameterCondition>().in("param_id", list));
    }
}
