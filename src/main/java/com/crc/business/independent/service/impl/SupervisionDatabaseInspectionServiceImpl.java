package com.crc.business.independent.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.SupervisionDatabaseInspectionDao;
import com.crc.business.independent.dao.TDictionariesDetailedDao;
import com.crc.business.independent.dao.UploadLinkAddressDao;
import com.crc.business.independent.model.entity.SupervisionDatabaseInspection;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.independent.model.vo.request.DatabaseInspectionParam;
import com.crc.business.independent.model.vo.result.DatabaseStatisticsVo;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.model.vo.result.SdIAndUpVo;
import com.crc.business.independent.model.vo.result.SupervisionDetailsVo;
import com.crc.business.independent.service.SupervisionDatabaseInspectionService;
import com.crc.business.independent.service.TDictionariesDetailedService;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.ExcelUtil;
import com.crc.common.utils.ResultVoUtil;
import com.crc.common.utils.StreamUtil;
import com.crc.common.utils.UserInformationUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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

@Slf4j
@Service("supervisionDatabaseService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupervisionDatabaseInspectionServiceImpl
        extends ServiceImpl<SupervisionDatabaseInspectionDao, SupervisionDatabaseInspection>
        implements SupervisionDatabaseInspectionService {

  private final SupervisionDatabaseInspectionDao supervisionDatabaseInspectionDao;
  private final UploadLinkAddressDao uploadLinkAddressDao;
  private final TDictionariesDetailedDao tDictionariesDetailedDao;
  private final TDictionariesDetailedService tDictionariesDetailedService;

  @Override
  public ResultVo findPageList(DatabaseInspectionParam supervisionDatabaseVo) {
    QueryWrapper<SupervisionDatabaseInspection> sdQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getCaseNumber())) {
      sdQueryWrapper.like("case_number", supervisionDatabaseVo.getCaseNumber());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getViolationType())) {
      sdQueryWrapper.eq("violation_type", supervisionDatabaseVo.getViolationType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getPunishmentType())) {
      sdQueryWrapper.eq("punishment_type", supervisionDatabaseVo.getPunishmentType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getHandleStatus())) {
      sdQueryWrapper.eq("handle_status", supervisionDatabaseVo.getHandleStatus());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getProblemClueField())) {
      sdQueryWrapper.eq("problem_clue_field", supervisionDatabaseVo.getProblemClueField());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getEndTime())) {
      sdQueryWrapper.le("problem_clue_time", supervisionDatabaseVo.getEndTime());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getStartTime())) {
      sdQueryWrapper.ge("problem_clue_time", supervisionDatabaseVo.getStartTime());
    }
    sdQueryWrapper.orderByDesc("id");
    Page<SupervisionDatabaseInspection> page =
            new Page<>(supervisionDatabaseVo.getPageNum(), supervisionDatabaseVo.getPageSize());
    Page<SupervisionDatabaseInspection> supervisionDatabasePage =
            supervisionDatabaseInspectionDao.selectPage(page, sdQueryWrapper);
    // 封装基础信息和附件---返回前端对象
    List<SdIAndUpVo> sdList = new ArrayList<>();
    for (SupervisionDatabaseInspection sd : supervisionDatabasePage.getRecords()) {
      SdIAndUpVo sdAndUpVo = new SdIAndUpVo();
      // 附件信息
      QueryWrapper<UploadLinkAddress> upQueryWrapper = new QueryWrapper<>();
      upQueryWrapper.select().eq("business_type", "supervision_database_inspection");
      upQueryWrapper.select().eq("uid", sd.getId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upQueryWrapper);
      sdAndUpVo.setFilePathList(uploadLinkAddresses);
      BeanUtils.copyProperties(sd, sdAndUpVo);

      // 优化部门展示
      sdAndUpVo.setDepartment(UserInformationUtil.subString(sdAndUpVo.getDepartment()));
      sdList.add(sdAndUpVo);
    }
    // 封装返回分页参数
    Page<SdIAndUpVo> pageNew =
            new Page<>(supervisionDatabaseVo.getPageNum(), supervisionDatabaseVo.getPageSize());
    pageNew.setRecords(sdList);
    return ResultVoUtil.success(page);
  }

  @Override
  public ResultVo findDetails(Long id) {
    SupervisionDetailsVo supervisionDetailsvO = new SupervisionDetailsVo();
    // 基本信息
    SupervisionDatabaseInspection supervisionDatabase =
            supervisionDatabaseInspectionDao.selectById(id);
    if (ObjectUtils.isEmpty(supervisionDatabase)) {
      throw new ServiceException(ErrorCodeEnum.DATA_ERROR);
    }

    // 优化部门展示
    supervisionDatabase.setDepartment(UserInformationUtil.subString(supervisionDatabase.getDepartment()));
    supervisionDetailsvO.setSupervisionDatabase(supervisionDatabase);
    QueryWrapper<UploadLinkAddress> upQueryWrapper = new QueryWrapper<>();
    upQueryWrapper.select().eq("uid", id);
    upQueryWrapper.select().eq("business_type", "supervision_database_inspection");
    List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upQueryWrapper);
    supervisionDetailsvO.setUploadLinkAddress(uploadLinkAddresses);

    return ResultVoUtil.success(supervisionDetailsvO);
  }

  @Override
  public List<SupervisionDatabaseInspection> findByExprotList(
          DatabaseInspectionParam supervisionDatabaseVo) {
    QueryWrapper<SupervisionDatabaseInspection> suQueryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getCaseNumber())) {
      suQueryWrapper.like("case_number", supervisionDatabaseVo.getCaseNumber());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getViolationType())) {
      suQueryWrapper.eq("violation_type", supervisionDatabaseVo.getViolationType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getPunishmentType())) {
      suQueryWrapper.eq("punishment_type", supervisionDatabaseVo.getPunishmentType());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getHandleStatus())) {
      suQueryWrapper.eq("handle_status", supervisionDatabaseVo.getHandleStatus());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getProblemClueField())) {
      suQueryWrapper.eq("problem_clue_field", supervisionDatabaseVo.getProblemClueField());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getEndTime())) {
      suQueryWrapper.le("problem_clue_time", supervisionDatabaseVo.getEndTime());
    }
    if (StringUtils.isNotEmpty(supervisionDatabaseVo.getStartTime())) {
      suQueryWrapper.ge("problem_clue_time", supervisionDatabaseVo.getStartTime());
    }
    suQueryWrapper.orderByDesc("id");
    List<SupervisionDatabaseInspection> byExprotList =
            supervisionDatabaseInspectionDao.selectList(suQueryWrapper);
    return byExprotList;
  }

  @Override
  public SupervisionDatabaseInspection findExprotSupervisionDatabase(
          Sheet sheet, Row row, Integer colNum, ExcelUtil excelUtil) {
    SupervisionDatabaseInspection supervisionDatabase = new SupervisionDatabaseInspection();
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    excelUtil.validCellValueIn(sheet, row, ++colNum, "年份");
    supervisionDatabase.setYear(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "办理状态");
    // 查询字典--处理状态
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed handleStatus = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(handleStatus)) {
      supervisionDatabase.setHandleStatus(handleStatus.getTypeCode());
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "问题线索时间");
    try {
      supervisionDatabase.setProblemClueTime
              (DoubleToDate(Integer.valueOf(excelUtil.getCellValue(sheet, row, colNum - 1))));
    } catch (Exception e) {
      try {
        supervisionDatabase.setProblemClueTime(
                sdf.parse(excelUtil.getCellValue(sheet, row, colNum - 1)));
      } catch (ParseException x) {
        throw new ServiceException("error", "上传的日期格式不正确");
      }
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "案件编号");
    supervisionDatabase.setCaseNumber(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "案件名称");
    supervisionDatabase.setCaseName(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "案件概要");
    supervisionDatabase.setCaseContent(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "初核是否属实");
    supervisionDatabase.setExamineType(
            StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "是") ? "yes" : "no");
    excelUtil.validCellValueIn(sheet, row, ++colNum, "是否立案");
    supervisionDatabase.setCaseEstablishType(
            StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "是") ? "yes" : "no");
    excelUtil.validCellValueIn(sheet, row, ++colNum, "问题线索涉及领域");
    // 查询字典--问题线索涉及领域
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed problemClueField = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(problemClueField)) {
      supervisionDatabase.setProblemClueField(problemClueField.getTypeCode());
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "违纪违规类别");
    // 查询字典--违纪违规类别
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed violationType = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(violationType)) {
      supervisionDatabase.setViolationType(violationType.getTypeCode());
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "处理人");
    supervisionDatabase.setHandlePeopleId(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "性别");
    if (StringUtils.equals("男", excelUtil.getCellValue(sheet, row, colNum - 1))) {
      supervisionDatabase.setSex("man");
    }
    if (StringUtils.equals("女", excelUtil.getCellValue(sheet, row, colNum - 1))) {
      supervisionDatabase.setSex("woman");
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "所在部门");
    supervisionDatabase.setDepartment(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "时任职务职级");
    supervisionDatabase.setRank(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "处分类别");
    // 查询字典--处分类别
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed punishmentType = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(punishmentType)) {
      supervisionDatabase.setPunishmentType(punishmentType.getTypeCode());
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "处分情况");
    supervisionDatabase.setPunishmentDetails(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "处分时间");
    try {
      supervisionDatabase.setPunishmentTime
              (DoubleToDate(Integer.valueOf(excelUtil.getCellValue(sheet, row, colNum - 1))));
    } catch (Exception e) {
      try {
        boolean equals = StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "");
        if (equals) {
          supervisionDatabase.setPunishmentTime(null);
        } else {
          supervisionDatabase.setPunishmentTime(
                  sdf.parse(excelUtil.getCellValue(sheet, row, colNum - 1)));
        }
      } catch (ParseException p) {
        throw new ServiceException("error", "上传的日期格式不正确");
      }
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "影响期至");
    try {
      supervisionDatabase.setValidityTime
              (DoubleToDate(Integer.valueOf(excelUtil.getCellValue(sheet, row, colNum - 1))));
    } catch (Exception e) {
      try {
        boolean equals = StringUtils.equals(excelUtil.getCellValue(sheet, row, colNum - 1), "");
        if (equals) {
          supervisionDatabase.setValidityTime(null);
        } else {
          supervisionDatabase.setValidityTime(
                  sdf.parse(excelUtil.getCellValue(sheet, row, colNum - 1)));
        }
      } catch (ParseException p) {
        throw new ServiceException("error", "上传的日期格式不正确");
      }
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "追缴违规所得");
    supervisionDatabase.setViolationMoney(excelUtil.getCellValue(sheet, row, colNum - 1));

    excelUtil.validCellValueIn(sheet, row, ++colNum, "挽回经济损失");
    supervisionDatabase.setToSaveMoney(excelUtil.getCellValue(sheet, row, colNum - 1));

    excelUtil.validCellValueIn(sheet, row, ++colNum, "降本金额");
    supervisionDatabase.setDropAmount(excelUtil.getCellValue(sheet, row, colNum - 1));

    excelUtil.validCellValueIn(sheet, row, ++colNum, "扣发奖金绩效");
    supervisionDatabase.setDeductionMoney(excelUtil.getCellValue(sheet, row, colNum - 1));

    excelUtil.validCellValueIn(sheet, row, ++colNum, "主动上缴");
    supervisionDatabase.setActiveTurnIn(excelUtil.getCellValue(sheet, row, colNum - 1));
    excelUtil.validCellValueIn(sheet, row, ++colNum, "线索来源");
    // 查询字典--线索来源
    tdQueryWrapper = new QueryWrapper<>();
    tdQueryWrapper.eq("type_name", excelUtil.getCellValue(sheet, row, colNum - 1));
    TDictionariesDetailed clueSource = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
    if (ObjectUtils.isNotEmpty(clueSource)) {
      supervisionDatabase.setClueSource(clueSource.getTypeCode());
    }
    excelUtil.validCellValueIn(sheet, row, ++colNum, "备注");
    supervisionDatabase.setRemark(excelUtil.getCellValue(sheet, row, colNum - 1));
    return supervisionDatabase;
  }

  @Override
  public void excel(Class<SdIAndUpVo> tClass, DatabaseInspectionParam supervisionDatabaseVo)
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
                              && !StringUtils.equals(annotation.value(), "链接表集合")) {
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
    String fileName = "InspectionExport" + formatTime;
    List<SupervisionDatabaseInspection> byExprotList = findByExprotList(supervisionDatabaseVo);
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
  public List<Map<String, Object>> createExcelRecord(
          List<SupervisionDatabaseInspection> supervisionDatabaseList) {
    List<Map<String, Object>> listmap = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    map.put("sheetName", "sheet1");
    listmap.add(map);
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    for (int j = 0; j < supervisionDatabaseList.size(); j++) {
      SupervisionDatabaseInspection sdAndUpVo = supervisionDatabaseList.get(j);
      Map<String, Object> mapValue = new HashMap<>();
      mapValue.put("year", sdAndUpVo.getYear() == null ? StringUtils.EMPTY : sdAndUpVo.getYear());
      tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "9");
      tdQueryWrapper.eq("type_code",
              sdAndUpVo.getHandleStatus() == null ? StringUtils.EMPTY : sdAndUpVo.getHandleStatus());
      TDictionariesDetailed handleStatus = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      mapValue.put("handleStatus",
              (handleStatus == null || handleStatus.getTypeName() == null)
                      ? StringUtils.EMPTY : handleStatus.getTypeName());
      mapValue.put("problemClueTime", sdAndUpVo.getProblemClueTime()
              == null ? StringUtils.EMPTY : sf.format(sdAndUpVo.getProblemClueTime()));
      mapValue.put("caseNumber",
              sdAndUpVo.getCaseNumber() == null ? StringUtils.EMPTY : sdAndUpVo.getCaseNumber());
      mapValue.put("caseName",
              sdAndUpVo.getCaseName() == null ? StringUtils.EMPTY : sdAndUpVo.getCaseName());
      mapValue.put("caseContent", sdAndUpVo.getCaseContent());
      mapValue.put("caseEstablishType",
              StringUtils.equals(sdAndUpVo.getCaseEstablishType(), "yes") ? "是" : "否");
      mapValue.put("examineType", StringUtils.equals(sdAndUpVo.getExamineType(), "yes") ? "是" : "否");
      tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "1");
      tdQueryWrapper.eq("type_code", sdAndUpVo.getProblemClueField() == null
              ? StringUtils.EMPTY : sdAndUpVo.getProblemClueField());
      TDictionariesDetailed problemClueField = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      mapValue.put("problemClueField",
              problemClueField.getTypeName() == null ? StringUtils.EMPTY : problemClueField.getTypeName());
      tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "2");
      tdQueryWrapper.eq("type_code",
              sdAndUpVo.getViolationType() == null ? StringUtils.EMPTY : sdAndUpVo.getViolationType());
      TDictionariesDetailed violationType = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      mapValue.put("violationType",
              violationType.getTypeName() == null ? StringUtils.EMPTY : violationType.getTypeName());
      mapValue.put("department",
              sdAndUpVo.getDepartment() == null ? StringUtils.EMPTY : sdAndUpVo.getDepartment());
      mapValue.put("rank", sdAndUpVo.getRank() == null ? StringUtils.EMPTY : sdAndUpVo.getRank());
      tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "3");
      tdQueryWrapper.eq("type_code", sdAndUpVo.getPunishmentType());
      TDictionariesDetailed punishmentType = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      mapValue.put("punishmentType",
              punishmentType.getTypeName() == null ? StringUtils.EMPTY : punishmentType.getTypeName());
      mapValue.put("handlePeopleId",
              sdAndUpVo.getHandlePeopleId() == null ? StringUtils.EMPTY : sdAndUpVo.getHandlePeopleId());
      mapValue.put("sex", StringUtils.equals(sdAndUpVo.getSex(), "man") ? "男人" : "女人");
      mapValue.put("punishmentDetails",
              sdAndUpVo.getPunishmentDetails() == null ? StringUtils.EMPTY : sdAndUpVo.getPunishmentDetails());
      mapValue.put("punishmentTime",
              sdAndUpVo.getPunishmentTime() == null ? StringUtils.EMPTY : sf.format(sdAndUpVo.getPunishmentTime()));
      mapValue.put("validityTime",
              sdAndUpVo.getValidityTime() == null ? StringUtils.EMPTY : sf.format(sdAndUpVo.getValidityTime()));
      mapValue.put("violationMoney",
              sdAndUpVo.getViolationMoney() == null ? StringUtils.EMPTY : sdAndUpVo.getViolationMoney());
      mapValue.put("toSaveMoney",
              sdAndUpVo.getToSaveMoney() == null ? StringUtils.EMPTY : sdAndUpVo.getToSaveMoney());
      mapValue.put("dropAmount",
              sdAndUpVo.getDropAmount() == null ? StringUtils.EMPTY : sdAndUpVo.getDropAmount());
      mapValue.put("deductionMoney",
              sdAndUpVo.getDeductionMoney() == null ? StringUtils.EMPTY : sdAndUpVo.getDeductionMoney());
      mapValue.put("activeTurnIn",
              sdAndUpVo.getActiveTurnIn() == null ? StringUtils.EMPTY : sdAndUpVo.getActiveTurnIn());
      tdQueryWrapper = new QueryWrapper<>();
      tdQueryWrapper.eq("classification_id", "4").eq("type_code",
              sdAndUpVo.getClueSource() == null ? StringUtils.EMPTY : sdAndUpVo.getClueSource());
      TDictionariesDetailed clueSource = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
      if (ObjectUtils.isNotEmpty(clueSource)) {
        mapValue.put("clueSource", clueSource.getTypeName());
      } else {
        mapValue.put("clueSource", StringUtils.EMPTY);
      }
      mapValue.put("remark", sdAndUpVo.getRemark() == null ? StringUtils.EMPTY : sdAndUpVo.getRemark());
      listmap.add(mapValue);
    }
    return listmap;
  }

  @Override
  public void add(SdIAndUpVo sdAndUpVo) {
    SupervisionDatabaseInspection supervisionDatabase = new SupervisionDatabaseInspection();
    BeanUtils.copyProperties(sdAndUpVo, supervisionDatabase);
    Date date = new Date();
    supervisionDatabase.setUpdateTime(date);
    supervisionDatabaseInspectionDao.insert(supervisionDatabase);
    if (!CollectionUtil.isEmpty(sdAndUpVo.getFileId()) && sdAndUpVo.getFileId().size() > 0) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", sdAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(supervisionDatabase.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  public void updateSuAndUpVo(SdIAndUpVo sdAndUpVo) {
    SupervisionDatabaseInspection supervisionDatabase = new SupervisionDatabaseInspection();
    BeanUtils.copyProperties(sdAndUpVo, supervisionDatabase);
    supervisionDatabase.setUpdateTime(new Date());
    supervisionDatabaseInspectionDao.updateById(supervisionDatabase);
    if (!CollectionUtil.isEmpty(sdAndUpVo.getFileId()) && sdAndUpVo.getFileId().size() > 0) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", sdAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(sdAndUpVo.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  public void addList(List<SupervisionDatabaseInspection> list) {
    Date date = new Date();
    for (SupervisionDatabaseInspection suList : list) {
      suList.setUpdateTime(date);
      supervisionDatabaseInspectionDao.insert(suList);
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
    List<SupervisionDatabaseInspection> list = new ArrayList<>();
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
        if (row.getRowNum() == 0 || row.getRowNum() == 1) {
          // 第一行表头跳过
          continue;
        }
      }
      rowNum++;
      colNum = 0;
      SupervisionDatabaseInspection exprotSupervisionDatabase =
              findExprotSupervisionDatabase(sheet, row, colNum, excelUtil);
      list.add(exprotSupervisionDatabase);
    }
    addList(list);
  }


  //解析Excel日期格式
  public static Date DoubleToDate(Integer dVal) {
    Calendar c = new GregorianCalendar(1900, 0, -1);
    Date d = c.getTime();
    Date date = DateUtils.addDays(d, dVal);
    return date;
  }

  @Override
  public List statisticsOne() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    String date = sdf.format(new Date());
    List<DatabaseStatisticsVo> list = supervisionDatabaseInspectionDao.statisticsOne(date);
    return list;
  }

  @Override
  public List statisticsTwo() {
    List<DatabaseStatisticsVo> list = supervisionDatabaseInspectionDao.statisticsTwo();
    list.stream().forEach(vo -> {
      TDictionariesDetailed punishmentType = tDictionariesDetailedDao.selectOne(
              new QueryWrapper<TDictionariesDetailed>()
                      .eq("classification_id", "1")
                      .eq("type_code", vo.getProblemClueField()));
      if (ObjectUtils.isNotEmpty(punishmentType)) {
        vo.setProblemClueField(punishmentType.getTypeName());
      }
    });
    return list;
  }

  @Override
  public Map statisticsThree() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    String date = sdf.format(new Date());
    List<DatabaseStatisticsVo> list = supervisionDatabaseInspectionDao.statisticsThree(date);
    Map<String, Map<String, Integer>> map = new HashMap<>();
    list.stream().forEach(vo -> {
      if (map.get(vo.getYear()) == null) {
        Map mapNew = new HashMap<>();
        mapNew.put(vo.getPunishmentType(), vo.getTotal());
        map.put(vo.getYear(), mapNew);
      } else {
        Map<String, Integer> map1 = map.get(vo.getYear());
        map1.put(vo.getPunishmentType(), vo.getTotal());
      }
    });
    List<TDictionariesDetailed> byClassificationId = tDictionariesDetailedService.findByClassificationId(3);
    Map data = new HashMap<>();
    data.put("statistics", map);
    data.put("punishmentType", byClassificationId);
    return data;
  }

  @Override
  public List statisticsFour() {
    List<DatabaseStatisticsVo> list = supervisionDatabaseInspectionDao.statisticsFour();
    return list;
  }
}
