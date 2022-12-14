package com.crc.business.independent.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.SupervisionDatabaseBusinessDao;
import com.crc.business.independent.dao.TDictionariesDetailedDao;
import com.crc.business.independent.dao.UploadLinkAddressDao;
import com.crc.business.independent.model.entity.SupervisionDatabaseBusiness;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.independent.model.vo.request.DatabaseBusinessParam;
import com.crc.business.independent.model.vo.result.*;
import com.crc.business.independent.service.SupervisionDatabaseBusinessService;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.ExcelUtil;
import com.crc.common.utils.ResultVoUtil;
import com.crc.common.utils.StreamUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** @Description 纪检观察 @Date 2021/9/28 10:34 */
@Slf4j
@Service("supervisionDatabaseBusinessService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupervisionDatabaseBusinessServiceImpl
    extends ServiceImpl<SupervisionDatabaseBusinessDao, SupervisionDatabaseBusiness>
    implements SupervisionDatabaseBusinessService {

  private final SupervisionDatabaseBusinessDao supervisionDatabaseBusinessDao;
  private final UploadLinkAddressDao uploadLinkAddressDao;
  private final TDictionariesDetailedDao tDictionariesDetailedDao;

  @Override
  public ResultVo findPageList(DatabaseBusinessParam supervisionDatabaseBusinessVo) {
    QueryWrapper<SupervisionDatabaseBusiness> subQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getProblemNumber())) {
      subQueryWrapper.like("problem_number", supervisionDatabaseBusinessVo.getProblemNumber());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getYear())) {
      subQueryWrapper.like("year", supervisionDatabaseBusinessVo.getYear());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getProblemSource())) {
      subQueryWrapper.eq("problem_source", supervisionDatabaseBusinessVo.getProblemSource());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getTransferInspectionType())) {
      subQueryWrapper.eq(
          "transfer_inspection_type", supervisionDatabaseBusinessVo.getTransferInspectionType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getDepartment())) {
      subQueryWrapper.eq("department", supervisionDatabaseBusinessVo.getDepartment());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getEndTime())) {
      subQueryWrapper.le("problem_time", supervisionDatabaseBusinessVo.getEndTime());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getStartTime())) {
      subQueryWrapper.ge("problem_time", supervisionDatabaseBusinessVo.getStartTime());
    }
    subQueryWrapper.orderByDesc("id");
    Page<SupervisionDatabaseBusiness> page =
        new Page<>(
            supervisionDatabaseBusinessVo.getPageNum(),
            supervisionDatabaseBusinessVo.getPageSize());
    Page<SupervisionDatabaseBusiness> supervisionDatabasePage =
        supervisionDatabaseBusinessDao.selectPage(page, subQueryWrapper);
    // 封装基础信息和附件---返回前端对象
    List<SdIAndUpVo> sdList = new ArrayList<>();
    for (SupervisionDatabaseBusiness sd : supervisionDatabasePage.getRecords()) {
      SdIAndUpVo sdAndUpVo = new SdIAndUpVo();
      QueryWrapper<UploadLinkAddress> upQueryWrapper = new QueryWrapper<>();
      upQueryWrapper.select().eq("business_type", "supervision_database_business");
      upQueryWrapper.select().eq("uid", sd.getId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upQueryWrapper);
      sdAndUpVo.setFilePathList(uploadLinkAddresses);
      BeanUtils.copyProperties(sd, sdAndUpVo);
      sdList.add(sdAndUpVo);
    }
    Page<SdIAndUpVo> pageNew =
        new Page<>(
            supervisionDatabaseBusinessVo.getPageNum(),
            supervisionDatabaseBusinessVo.getPageSize());
    pageNew.setRecords(sdList);
    return ResultVoUtil.success(page);
  }

  @Override
  public ResultVo findDetails(Long id) {
    SupervisionDetailsVo supervisionDetailsVo = new SupervisionDetailsVo();
    SupervisionDatabaseBusiness supervisionDatabaseBusiness =
        supervisionDatabaseBusinessDao.selectById(id);
    if (ObjectUtils.isEmpty(supervisionDatabaseBusiness)) {
      throw new ServiceException(ErrorCodeEnum.DATA_ERROR);
    }
    supervisionDetailsVo.setSupervisionDatabaseBusiness(supervisionDatabaseBusiness);
    // 附件信息
    QueryWrapper<UploadLinkAddress> upQueryWrapper = new QueryWrapper<>();
    upQueryWrapper.select().eq("uid", id);
    upQueryWrapper.select().eq("business_type", "supervision_database_business");
    List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upQueryWrapper);
    supervisionDetailsVo.setUploadLinkAddress(uploadLinkAddresses);

    return ResultVoUtil.success(supervisionDetailsVo);
  }

  @Override
  public void add(SdbAndUpVo sdbAndUpVo) {
    SupervisionDatabaseBusiness supervisionDatabaseBusiness = new SupervisionDatabaseBusiness();
    // 生成随机单据编号----BS+时间+随机四位数
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    StringBuffer stringBuffer = new StringBuffer();
    String parse = sdf.format(new Date());
    stringBuffer.append("BS").append(parse).append("_");

    // 查询上条订单的订单号
    QueryWrapper<SupervisionDatabaseBusiness> suQueryWrapper = new QueryWrapper<>();
    suQueryWrapper.orderByDesc("id");
    suQueryWrapper.last("limit 1");
    SupervisionDatabaseBusiness supervisionDatabaseBusinessNew =
        supervisionDatabaseBusinessDao.selectOne(suQueryWrapper);
    if (ObjectUtils.isEmpty(supervisionDatabaseBusinessNew)) {
      stringBuffer.append("00001");
    } else {
      Integer number =
          Integer.valueOf(supervisionDatabaseBusinessNew.getProblemNumber().substring(12, 16));
      // 编号到99999后初始化为1
      if (number >= 99999) {
        stringBuffer.append("00001");
      } else {
        number = number + 1;
        String format = String.format("%05d", number);
        stringBuffer.append(format);
      }
    }
    sdbAndUpVo.setProblemNumber(stringBuffer.toString());
    BeanUtils.copyProperties(sdbAndUpVo, supervisionDatabaseBusiness);
    Date date = new Date();
    supervisionDatabaseBusiness.setUpdateTime(date);
    supervisionDatabaseBusiness.setCreatTime(date);
    supervisionDatabaseBusinessDao.insert(supervisionDatabaseBusiness);
    if (!CollectionUtil.isEmpty(sdbAndUpVo.getFileId()) && sdbAndUpVo.getFileId().size() > 0) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", sdbAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(supervisionDatabaseBusiness.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  public void updateSuAndUpVo(SdbAndUpVo sdbAndUpVo) {
    SupervisionDatabaseBusiness supervisionDatabaseBusiness = new SupervisionDatabaseBusiness();
    BeanUtils.copyProperties(sdbAndUpVo, supervisionDatabaseBusiness);
    supervisionDatabaseBusiness.setUpdateTime(new Date());
    supervisionDatabaseBusinessDao.updateById(supervisionDatabaseBusiness);
    if (!CollectionUtil.isEmpty(sdbAndUpVo.getFileId()) && sdbAndUpVo.getFileId().size() > 0) {
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", sdbAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(sdbAndUpVo.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  public void excel(Class<SdbAndUpVo> tClass, DatabaseBusinessParam databaseBusinessSeachVo)
      throws IOException {
    Field[] declaredFields = tClass.getDeclaredFields();
    List key = new ArrayList<>();
    List value = new ArrayList<>();
    Arrays.stream(declaredFields)
        .forEach(
            declaredField -> {
              ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
              if (!StringUtils.equals(annotation.value(), "id")
                  && !StringUtils.equals(annotation.value(), "链接表路径集合")
                  && !StringUtils.equals(annotation.value(), "链接表集合")
                  && !StringUtils.equals(annotation.value(), "部门id")) {
                key.add(annotation.value());
                value.add(declaredField.getName());
              }
            });
    // 行名列名
    String[] columnNames = new String[key.size()];
    key.toArray(columnNames);
    String[] keys = new String[value.size()];
    value.toArray(keys);
    ServletRequestAttributes requestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletResponse res = requestAttributes.getResponse();
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String formatTime = sdf.format(d);
    String fileName = "BusinessExport" + formatTime;
    List<SupervisionDatabaseBusiness> byExprotList = findByExprotList(databaseBusinessSeachVo);
    if (CollectionUtil.isEmpty(byExprotList)) {
      new ServiceException("error", "导出表格为空");
    }
    List<Map<String, Object>> list = createExcelRecord(byExprotList);
    if (CollectionUtil.isEmpty(list)) {
      new ServiceException("error", "生成excel数据为空");
    }
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
    } catch (IOException e) {
      log.info("ldapuid:{}", "创建文档异常");
    }
    byte[] content = os.toByteArray();
    InputStream is = new ByteArrayInputStream(content);

    // 设置response参数，可以打开下载页面
    res.reset();
    res.setContentType("application/vnd.ms-excel;charset=UTF-8");
    res.setHeader(
        "Content-Disposition",
        "attachment;filename=" + java.net.URLDecoder.decode(fileName + ".xlsx", "UTF-8"));
    ServletOutputStream out = res.getOutputStream();
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      bis = new BufferedInputStream(is);
      bos = new BufferedOutputStream(out);
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        bos.write(buff, 0, bytesRead);
      }
    } catch (final IOException e) {
      log.info("ldapuid:{}", "stream流异常");
    } finally {
      StreamUtil.closeStream(bis);
      StreamUtil.closeStream(bos);
    }
  }

  @Override
  public List<SupervisionDatabaseBusiness> findByExprotList(
      DatabaseBusinessParam supervisionDatabaseBusinessVo) {
    QueryWrapper<SupervisionDatabaseBusiness> subQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getProblemNumber())) {
      subQueryWrapper.like("problem_number", supervisionDatabaseBusinessVo.getProblemNumber());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getYear())) {
      subQueryWrapper.like("year", supervisionDatabaseBusinessVo.getYear());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getProblemSource())) {
      subQueryWrapper.eq("problem_source", supervisionDatabaseBusinessVo.getProblemSource());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getTransferInspectionType())) {
      subQueryWrapper.eq(
          "transfer_inspection_type", supervisionDatabaseBusinessVo.getTransferInspectionType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getDepartment())) {
      subQueryWrapper.eq("department", supervisionDatabaseBusinessVo.getDepartment());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getEndTime())) {
      subQueryWrapper.le("problem_time", supervisionDatabaseBusinessVo.getEndTime());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseBusinessVo.getStartTime())) {
      subQueryWrapper.ge("problem_time", supervisionDatabaseBusinessVo.getStartTime());
    }
    subQueryWrapper.orderByDesc("id");
    List<SupervisionDatabaseBusiness> byExprotList =
        supervisionDatabaseBusinessDao.selectList(subQueryWrapper);
    return byExprotList;
  }

  @Override
  public SupervisionDatabaseBusiness findExprotSupervisionDatabase(
      Sheet sheet, Row row, Integer colNum, ExcelUtil excelUtil) {
    SupervisionDatabaseBusiness supervisionDatabaseBusiness = new SupervisionDatabaseBusiness();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    excelUtil.validCellValueBu(sheet, row, ++colNum, "年份");
    supervisionDatabaseBusiness.setYear(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueBu(sheet, row, ++colNum, "部门");
    supervisionDatabaseBusiness.setDepartment(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueBu(sheet, row, ++colNum, "问题来源");
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    // 查询字典--问题来源
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed problemClueField = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(problemClueField)) {
      supervisionDatabaseBusiness.setProblemSource(problemClueField.getTypeCode());
    }
    excelUtil.validCellValueBu(sheet, row, ++colNum, "问题时间");
    try {
      supervisionDatabaseBusiness.setProblemTime(
          sdf.parse(excelUtil.getCellValue(sheet, row, colNum - 1)));
    } catch (ParseException e) {
      throw new ServiceException("error", "上传的日期格式不正确");
    }
    excelUtil.validCellValueBu(sheet, row, ++colNum, "经办人");
    supervisionDatabaseBusiness.setHandlerName(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueBu(sheet, row, ++colNum, "是否移交纪检");
    supervisionDatabaseBusiness.setTransferInspectionType(
        StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "是") ? "yes" : "no");
    excelUtil.validCellValueBu(sheet, row, ++colNum, "问题描述");
    supervisionDatabaseBusiness.setProblemDescribe(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueBu(sheet, row, ++colNum, "办结时间");
    try {
      boolean equals = StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "");
      if (equals) {
        supervisionDatabaseBusiness.setFinishTime(null);
      } else {
        supervisionDatabaseBusiness.setFinishTime(
            sdf.parse(excelUtil.getCellValue(sheet, row, colNum - 1)));
      }
    } catch (ParseException e) {
      throw new ServiceException("error", "上传的日期格式不正确");
    }
    excelUtil.validCellValueBu(sheet, row, ++colNum, "办结结果");
    supervisionDatabaseBusiness.setFinishResult(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueBu(sheet, row, ++colNum, "备注");
    supervisionDatabaseBusiness.setRemark(excelUtil.getCellValue(sheet, row, colNum - 1));
    return supervisionDatabaseBusiness;
  }

  @Override
  public void addList(List<SupervisionDatabaseBusiness> list) {
    Integer number = null;
    for (SupervisionDatabaseBusiness subList : list) {
      // 生成随机单据编号----BS+时间+随机四位数
      SupervisionDatabaseBusiness supervisionDatabaseBusiness = new SupervisionDatabaseBusiness();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      StringBuffer stringBuffer = new StringBuffer();
      String parse = sdf.format(new Date());
      stringBuffer.append("BS").append(parse).append("_");
      QueryWrapper<SupervisionDatabaseBusiness> suQueryWrapper = new QueryWrapper<>();
      suQueryWrapper.orderByDesc("id");
      suQueryWrapper.last("limit 1");
      SupervisionDatabaseBusiness supervisionDatabaseBusinessNew =
          supervisionDatabaseBusinessDao.selectOne(suQueryWrapper);
      if (ObjectUtils.isEmpty(supervisionDatabaseBusinessNew)) {
        stringBuffer.append("00001");
      } else {
        Integer numberNew =
            Integer.valueOf(supervisionDatabaseBusinessNew.getProblemNumber().substring(11, 16));
        if (numberNew >= 99999) {
          stringBuffer.append("00001");
        } else {
          number = (number == null ? numberNew + 1 : number + 1);
          String format = String.format("%05d", number);
          stringBuffer.append(format);
        }
      }
      subList.setProblemNumber(stringBuffer.toString());
      BeanUtils.copyProperties(subList, supervisionDatabaseBusiness);
      supervisionDatabaseBusiness.setUpdateTime(new Date());
      supervisionDatabaseBusiness.setCreatTime(new Date());
      supervisionDatabaseBusinessDao.insert(supervisionDatabaseBusiness);
    }
  }

  @Override
  public void importFile(MultipartFile file) {
    int rowNum = 0;
    int colNum = 0;
    int realRowCount = 0;
    ExcelUtil excelUtil = new ExcelUtil();
    // 得到工作空间
    Workbook workbook = null;
    try {
      workbook =
          excelUtil.getWorkbookByInputStream(file.getInputStream(), file.getOriginalFilename());
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 得到工作表
    Sheet sheet = excelUtil.getSheetByWorkbook(workbook, 0);
    if (sheet.getRow(2000) != null) {
      throw new RuntimeException("系统已限制单批次导入必须小于或等于2000笔！");
    }
    realRowCount = sheet.getPhysicalNumberOfRows();
    List<SupervisionDatabaseBusiness> list = new ArrayList<>();
    for (Row row : sheet) {
      if (realRowCount == rowNum) {
        break;
      }
      if (excelUtil.isBlankRow(row)) {
        // 空行跳过
        continue;
      }
      if (row.getRowNum() == -1) {
        continue;
      } else {
        if (row.getRowNum() == 0) {
          // 第一行表头跳过
          continue;
        }
      }
      rowNum++;
      colNum = 0;
      SupervisionDatabaseBusiness exprotSupervisionDatabase =
          findExprotSupervisionDatabase(sheet, row, colNum, excelUtil);
      list.add(exprotSupervisionDatabase);
    }
    addList(list);
  }

  public List<Map<String, Object>> createExcelRecord(
      List<SupervisionDatabaseBusiness> supervisionDatabaseList) {
    List<Map<String, Object>> listmap = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    map.put("sheetName", "sheet1");
    listmap.add(map);
    // 获得SimpleDateFormat类
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SupervisionDatabaseBusiness sdbAndUpVo = new SupervisionDatabaseBusiness();
    for (int j = 0; j < supervisionDatabaseList.size(); j++) {
      sdbAndUpVo = supervisionDatabaseList.get(j);
      Map<String, Object> mapValue = new HashMap<>();
      mapValue.put("problemNumber", sdbAndUpVo.getProblemNumber());
      mapValue.put("year", sdbAndUpVo.getYear() == null ? StringUtils.EMPTY : sdbAndUpVo.getYear());
      mapValue.put(
          "department",
          sdbAndUpVo.getDepartment() == null ? StringUtils.EMPTY : sdbAndUpVo.getDepartment());

      // 查询字典--问题来源
      QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "5");
      tdQueryWrapper.eq(
          "type_code",
          sdbAndUpVo.getProblemSource() == null
              ? StringUtils.EMPTY
              : sdbAndUpVo.getProblemSource());
      TDictionariesDetailed problemSource = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      if (ObjectUtils.isNotEmpty(problemSource)) {
        mapValue.put("problemSource", problemSource.getTypeName());
      } else {
        mapValue.put("problemSource", StringUtils.EMPTY);
      }
      mapValue.put(
          "problemTime",
          sdbAndUpVo.getProblemTime() == null
              ? StringUtils.EMPTY
              : sf.format(sdbAndUpVo.getProblemTime()));
      mapValue.put(
          "handlerName",
          sdbAndUpVo.getHandlerName() == null ? StringUtils.EMPTY : sdbAndUpVo.getHandlerName());
      mapValue.put(
          "transferInspectionType",
          StringUtils.equals(sdbAndUpVo.getTransferInspectionType(), "yes") ? "是" : "否");
      mapValue.put(
          "problemDescribe",
          sdbAndUpVo.getProblemDescribe() == null
              ? StringUtils.EMPTY
              : sdbAndUpVo.getProblemDescribe());
      mapValue.put(
          "finishTime",
          sdbAndUpVo.getFinishTime() == null
              ? StringUtils.EMPTY
              : sf.format(sdbAndUpVo.getFinishTime()));
      mapValue.put(
          "finishResult",
          sdbAndUpVo.getFinishResult() == null ? StringUtils.EMPTY : sdbAndUpVo.getFinishResult());
      mapValue.put(
          "remark", sdbAndUpVo.getRemark() == null ? StringUtils.EMPTY : sdbAndUpVo.getRemark());
      listmap.add(mapValue);
    }
    return listmap;
  }
}
