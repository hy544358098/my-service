package com.x.business.independent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.x.business.independent.model.entity.AnnouncementManagement;
import com.x.business.independent.model.vo.request.AnnouncementParamPO;
import com.x.business.independent.model.vo.request.AnnouncementSearchParam;

import java.util.List;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/18 15:53
 * @Version: 1.0
 */
public interface AnnouncementService extends IService<AnnouncementManagement> {
    /**
     * @param announcementParam 数据实体
     * @param userId 用户usr_login
     * @Description TODO
     * @Date 2021/10/22 12:27
     * @Return void
     */
    void save(AnnouncementParamPO announcementParam, String userId);

    /**
     * @param list uid集合
     * @Description 删除
     * @Date 2021/10/22 12:26
     * @Return void
     */
    void delete(List<String> list);

    /**
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.crc.business.Independent.model.vo.result.AnnouncementVO>
     * @Author xiaoming
     * @Description TODO
     * @Date 16:43 2021/10/18
     * @Param [announcementSearchParam]
     */
    Page<AnnouncementManagement> list(AnnouncementSearchParam announcementSearchParam);
}
