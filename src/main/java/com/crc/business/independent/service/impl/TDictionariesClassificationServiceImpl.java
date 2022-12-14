package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.TDictionariesClassificationDao;
import com.crc.business.independent.model.entity.TDictionariesClassification;
import com.crc.business.independent.service.TDictionariesClassificationService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoming
 */
@Service("tDictionariesClassificationService")
public class TDictionariesClassificationServiceImpl
    extends ServiceImpl<TDictionariesClassificationDao, TDictionariesClassification>
    implements TDictionariesClassificationService {}
