package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.oa.model.entity.ProjectChangeEntity;
import com.crc.business.oa.model.vo.request.ProjectChangeParam;

import java.util.List;

/**
 * @Description OA工程变更
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
public interface ProjectChangeService extends IService<ProjectChangeEntity> {

  /**
   * @Description 统计图表
   * @Date 2021/12/1 9:58
   * @param
   * @Return {@link java.lang.Object}
   */
    List statistics(ProjectChangeParam projectChangeParam);
}
