package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.SupervisionDatabaseInspection;
import com.crc.business.independent.model.vo.request.DatabaseInspectionParam;
import com.crc.business.independent.model.vo.result.*;
import com.crc.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2021-09-18 11:31:02
 */
public interface SupervisionDatabaseInspectionService
        extends IService<SupervisionDatabaseInspection> {

  /**
   * @Description 列表分页查询
   * @Date 2021/10/26 15:08
   * @param supervisionDatabaseVo
   * @Return {@link ResultVo}
   */
  ResultVo findPageList(DatabaseInspectionParam supervisionDatabaseVo);

  /**
   * @Description 新增
   * @Date 2021/10/26 15:08
   * @param sdAndUpVo
   * @Return
   */
  void add(SdIAndUpVo sdAndUpVo);

  /**
   * @Description 修改
   * @Date 2021/10/26 15:08
   * @param sdAndUpVo
   * @Return
   */
  void updateSuAndUpVo(SdIAndUpVo sdAndUpVo);

  /**
   * @Description 详情查询
   * @Date 2021/10/26 15:08
   * @param id
   * @Return {@link ResultVo}
   */
  ResultVo findDetails(Long id);

  /**
   * @Description 导出excel数据
   * @Date 2021/10/26 15:07
   * @param tClass
   * @param supervisionDatabaseVo
   * @Return
   */
  void excel(Class<SdIAndUpVo> tClass, DatabaseInspectionParam supervisionDatabaseVo) throws IOException;

  /**
   * 生成Excel数据
   *
   * @param supervisionDatabaseList
   * @return
   */
  List<Map<String, Object>> createExcelRecord(List<SupervisionDatabaseInspection> supervisionDatabaseList);

  /**
   * @Description 导出表格的列表查询
   * @Date 2021/10/26 15:07
   * @param supervisionDatabaseVo
   * @Return {@link List< SupervisionDatabaseInspection>}
   */
  List<SupervisionDatabaseInspection> findByExprotList(DatabaseInspectionParam supervisionDatabaseVo);

  /**
   * @Description 导入表格
   * @Date 2021/10/26 15:07
   * @param sheet
   * @param row
   * @param colNum
   * @param excelUtil
   * @Return {@link SupervisionDatabaseInspection}
   */
  SupervisionDatabaseInspection findExprotSupervisionDatabase(
          Sheet sheet, Row row, Integer colNum, ExcelUtil excelUtil);

  /**
   * @Description 批量新增
   * @Date 2021/10/26 15:07
   * @param list
   * @Return
   */
  void addList(List<SupervisionDatabaseInspection> list);

  /**
   * @Description 导入数据
   * @Date 2021/10/26 15:07
   * @param file
   * @Return
   */
  void importFile(MultipartFile file);

  /**
   * @Description 统计图表一
   * @Date 2021/11/23 10:59
   * @param
   * @Return
   */
  List statisticsOne();

  /**
   * @Description 统计图表二
   * @Date 2021/11/23 15:07
   * @param
   * @Return
   */
  List statisticsTwo();

  /**
   * @Description 统计图表三
   * @Date 2021/11/23 15:18
   * @param
   * @Return {@link List}
   */
  Map statisticsThree();

  /**
   * @Description 统计图表四
   * @Date 2021/11/23 15:48
   * @param
   * @Return {@link List}
   */
  List statisticsFour();
}
