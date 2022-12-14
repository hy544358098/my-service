package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.SupervisionDatabaseBusiness;
import com.crc.business.independent.model.vo.request.DatabaseBusinessParam;
import com.crc.business.independent.model.vo.result.*;
import com.crc.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xiaoming
 * @date 2021-09-23 13:35:59
 */
public interface SupervisionDatabaseBusinessService extends IService<SupervisionDatabaseBusiness> {


  /**
   * @Description 列表分页查询
   * @Date 2021/10/26 15:05
   * @param supervisionDatabaseBusinessVo
   * @Return {@link ResultVo}
   */
  ResultVo findPageList(DatabaseBusinessParam supervisionDatabaseBusinessVo);

  /**
   * @Description 详情查询
   * @Date 2021/10/26 15:05
   * @param id
   * @Return {@link ResultVo}
   */
  ResultVo findDetails(Long id);

  /**
   * @Description 新增
   * @Date 2021/10/26 15:05
   * @param sdbAndUpVo
   * @Return
   */
  void add(SdbAndUpVo sdbAndUpVo);

  /**
   * @Description 修改
   * @Date 2021/10/26 15:05
   * @param sdbAndUpVo
   * @Return
   */
  void updateSuAndUpVo(SdbAndUpVo sdbAndUpVo);

  /**
   * @Description 导出excel数据
   * @Date 2021/10/26 15:06
   * @param tClass
   * @param databaseBusinessSeachVo
   * @Return
   */
  void excel(Class<SdbAndUpVo> tClass, DatabaseBusinessParam databaseBusinessSeachVo) throws IOException;

  /**
   * @Description 导出表格的列表查询
   * @Date 2021/10/26 15:05
   * @param supervisionDatabaseBusinessVo
   * @Return {@link List< SupervisionDatabaseBusiness>}
   */
  List<SupervisionDatabaseBusiness> findByExprotList(DatabaseBusinessParam supervisionDatabaseBusinessVo);

  /**
   * @Description 导入表格
   * @Date 2021/10/26 15:06
   * @param sheet
   * @param row
   * @param colNum
   * @param excelUtil
   * @Return {@link SupervisionDatabaseBusiness}
   */
  SupervisionDatabaseBusiness findExprotSupervisionDatabase(
          Sheet sheet, Row row, Integer colNum, ExcelUtil excelUtil);

  /**
   * @Description 批量新增
   * @Date 2021/10/26 15:06
   * @param list
   * @Return
   */
  void addList(List<SupervisionDatabaseBusiness> list);

  /**
   * @Description 导入数据
   * @Date 2021/10/26 15:06
   * @param file
   * @Return
   */
  void importFile(MultipartFile file);
}
