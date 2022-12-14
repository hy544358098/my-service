package com.crc.business.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.oa.dao.ProjectChangeDao;
import com.crc.business.oa.model.entity.ProjectChangeEntity;
import com.crc.business.oa.model.vo.request.ProjectChangeParam;
import com.crc.business.oa.model.vo.result.ProjectChangeStatisticsVo;
import com.crc.business.oa.service.ProjectChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service("tOaProjectChangeService")
public class ProjectChangeServiceImpl extends ServiceImpl<ProjectChangeDao, ProjectChangeEntity>
    implements ProjectChangeService {

  private final ProjectChangeDao projectChangeDao;


  @Override
  public List statistics(ProjectChangeParam projectChangeParam) {
    List<ProjectChangeStatisticsVo> statistics = projectChangeDao.statistics(projectChangeParam);
    CommonServiceImpl.DEPT_MAP.keySet().stream()
            .forEach(
                    set -> {
                      boolean flag = true;
                      for (ProjectChangeStatisticsVo list : statistics) {
                        if (StringUtils.equals(
                                CommonServiceImpl.DEPT_MAP.get(set), list.getStatisticsCityName())) {
                          flag = false;
                        }
                      }
                      if (flag) {
                        ProjectChangeStatisticsVo projectChangeStatisticsVo = new ProjectChangeStatisticsVo();
                        projectChangeStatisticsVo.setStatisticsCityName(CommonServiceImpl.DEPT_MAP.get(set));
                        projectChangeStatisticsVo.setTotal(0);
                        statistics.add(projectChangeStatisticsVo);
                      }
                    });
    return statistics;
  }
}
