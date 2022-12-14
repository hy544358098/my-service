package com.crc.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.independent.model.entity.OperationDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
@Mapper
public interface OperationDetailDao extends BaseMapper<OperationDetail> {

  /**
   * 转办知会的流程信息限制
   *
   * @param supervisionId
   * @param operationPeopleId
   * @param roleName
   * @return
   */
  List<OperationDetail> authority(
      @Param("supervisionId") String supervisionId,
      @Param("operationPeopleId") String operationPeopleId,
      @Param("roleName") String roleName,
      @Param("roleNameDepartment")String roleNameDepartment);


  /**
   * @Description 查询各我要观察类型的转办数据
   * @Date 2021/10/27 9:30
   * @param
   * @Return {@link List < SpecialOperateEntity>}
   */
  List<OperationDetail> timingRemind();

  /**
   * @Description 查询在此操作之后有没有反馈数据
   * @Date 2021/10/29 10:41
   * @param
   * @Return {@link String}
   */
  String findOperationTime(OperationDetail operationDetail);

  /**
   * @Description 查询操作权限
   * @Date 2021/12/13 15:18
   * @param supervisionId
   * @param operationPeopleId
   * @Return
   */
  List<OperationDetail> findCompetence(@Param("supervisionId") Integer supervisionId, @Param("operationPeopleId")String operationPeopleId);

  /**
   * @Description 查询操作权限
   * @Date 2021/12/13 15:18
   * @param personalList
   * @Return
   */
  List<String> findCompetenceNew(@Param("list") List<String> personalList,@Param("userId") String userId);
}
