package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.Supervision;
import com.crc.business.independent.model.entity.SupervisionExcel;
import com.crc.business.independent.model.vo.request.SupervisionParam;
import com.crc.business.independent.model.vo.result.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @Description 观察操作表
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
public interface SupervisionService extends IService<Supervision> {

  /**
   * @Description 列表查询
   * @Date 2021/10/22 13:35
   * @param supervisionVo
   * @param roleNames
   * @Return {@link Page< SupervisionVo>}
   */
  Page<SupervisionVo> list(SupervisionParam supervisionVo, String roleNames);


  /**
   * @Description 各状态数量查询
   * @Date 2021/10/22 13:35
   * @param supervisionVo
   * @param roleNames
   * @Return {@link MySupervisionCountVo}
   */
  MySupervisionCountVo statisticalData(SupervisionParam supervisionVo, String roleNames);

  /**
   * @Description 详情查询
   * @Date 2021/10/22 13:35
   * @param id
   * @param userId
   * @Return {@link ResultVo}
   */
  ResultVo findDetails(String id, String userId,String roleNames);

  /**
   * @Description 新增
   * @Date 2021/10/22 13:36
   * @param suAndUpVo
   * @param userId
   * @Return
   */
  void add(SuAndUpVo suAndUpVo,String userId);

  /**
   * @Description 修改
   * @Date 2021/10/26 11:31
   * @param suAndUpVo
   * @Return
   */
  void update(SuAndUpVo suAndUpVo);


  /**
   * @Description 修改观察表数据
   * @Date 2021/10/22 13:36
   * @param operationDetailVo
   * @Return
   */
  void operation(OperationDetailVo operationDetailVo);

  /**
   * @Description 导出excel数据
   * @Date 2021/10/22 13:50
   * @param tClass
   * @param supervisionVo
   * @param roleNames
   * @Return
   */
  void excel(Class<SupervisionExcel> tClass, SupervisionParam supervisionVo, String roleNames) throws IOException;


  /**
   * @Description 生成excel数据
   * @Date 2021/10/22 13:36
   * @param supervisionList
   * @Return {@link List< Map< String, Object>>}
   */
  List<Map<String, Object>> createExcelRecord(List<SupervisionVo> supervisionList);

  /**
   * @Description 导出表格的列表查询
   * @Date 2021/10/22 13:39
   * @param supervisionVo
   * @param roleNames
   * @Return {@link SupervisionVo>}
   * @Return {@link List<SupervisionVo>}
   */
  List<SupervisionVo> findByExprotList(SupervisionParam supervisionVo, String roleNames);

  /**
   * @Description 逻辑删除
   * @Date 2021/11/22 15:19
   * @param asList
   * @Return
   */
  void falseDelete(List<Long> asList);

  /**
   * @Description 统计图表一
   * @Date 2021/11/24 9:58
   * @param
   * @Return {@link Object}
   */
  List statisticsOne();

  /**
   * @Description 统计图表二
   * @Date 2021/11/24 9:58
   * @param
   * @Return {@link List}
   */
  List statisticsTwo();

  /**
   * @Description 统计图表三
   * @Date 2021/11/24 9:58
   * @param
   * @Return {@link List}
   */
  List statisticsThree();

  /**
   * @Description 置顶
   * @Date 2022/1/26 10:11
   * @param asList
   * @Return
   */
  void top(String id,Integer topType);
}
