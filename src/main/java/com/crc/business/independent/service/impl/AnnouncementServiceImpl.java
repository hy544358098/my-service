package com.crc.business.independent.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.AnnouncementDao;
import com.crc.business.independent.model.entity.AnnouncementManagement;
import com.crc.business.independent.model.vo.request.AnnouncementParamPO;
import com.crc.business.independent.model.vo.request.AnnouncementSearchParam;
import com.crc.business.independent.service.AnnouncementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: AnnouncementServiceImpl
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/18 16:02
 * @Version: 1.0
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementDao, AnnouncementManagement> implements AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public void save(AnnouncementParamPO announcementParam, String userId) {

        if (StringUtils.isNotEmpty(announcementParam.getUid())) {
            AnnouncementManagement announcementManagement = getOne(new QueryWrapper<AnnouncementManagement>().eq("uid", announcementParam.getUid()));
            announcementManagement.setContent(announcementParam.getContent());
            announcementManagement.setUpdateTime(LocalDateTime.now());
            announcementManagement.setUpdateUser(userId);
            announcementManagement.setTopType(announcementParam.getTopType());
            announcementManagement.setType(announcementParam.getType());
            announcementManagement.updateById();
        } else {
            AnnouncementManagement announcementManagement = new AnnouncementManagement();
            BeanUtils.copyProperties(announcementParam, announcementManagement);
            announcementManagement.setCreateTime(LocalDateTime.now());
            announcementManagement.setUpdateTime(announcementManagement.getCreateTime());
            announcementManagement.setCreateUser(userId);
            announcementManagement.setUpdateUser(userId);
            announcementManagement.setUid(IdUtil.createSnowflake(1, 1).nextIdStr());
            announcementManagement.setTopType(announcementParam.getTopType());
            announcementManagement.setType(announcementParam.getType());
            announcementManagement.insert();
        }
    } 

    @Override
    public void delete(List<String> list) {
        announcementDao.delete(new QueryWrapper<AnnouncementManagement>().in("uid", list));
    }

    @Override
    public Page<AnnouncementManagement> list(AnnouncementSearchParam announcementSearchParam) {
        Page<AnnouncementManagement> page = new Page<>(announcementSearchParam.getPageNum(), announcementSearchParam.getPageSize());
        QueryWrapper<AnnouncementManagement> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(announcementSearchParam.getContent())) {
            queryWrapper.like("content", announcementSearchParam.getContent());
        }
        if (StringUtils.isNotEmpty(announcementSearchParam.getStartTime())) {
            queryWrapper.ge("update_time", announcementSearchParam.getStartTime()+" 00:00:00");
        }
        if (StringUtils.isNotEmpty(announcementSearchParam.getEndTime())) {
            queryWrapper.lt("update_time", announcementSearchParam.getEndTime()+" 23:59:59");
        }
        if (null!=announcementSearchParam.getType()){
            queryWrapper.eq("type",announcementSearchParam.getType());
        }
        if (null!=announcementSearchParam.getTopType()){
            queryWrapper.eq("top_type",announcementSearchParam.getTopType());
        }
        queryWrapper.orderByDesc("top_type").orderByDesc("update_time");
        Page<AnnouncementManagement> announcementManagementList = announcementDao.selectPage(page, queryWrapper);
        return announcementManagementList;
    }
}
