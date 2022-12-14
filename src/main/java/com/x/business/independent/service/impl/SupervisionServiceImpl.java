package com.x.business.independent.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.business.independent.dao.*;
import com.x.business.independent.model.entity.*;
import com.x.business.independent.model.vo.RoleName;
import com.x.business.independent.model.vo.request.SupervisionParam;
import com.x.business.independent.model.vo.result.*;
import com.x.business.independent.service.OperationDetailService;
import com.x.business.independent.service.SupervisionService;
import com.x.business.oa.service.impl.CommonServiceImpl;
import com.x.common.enums.ErrorCodeEnum;
import com.x.common.enums.SupervisionCodeEnum;
import com.x.common.enums.SupervisionStatusEnum;
import com.x.common.exception.ServiceException;
import com.x.common.utils.*;
import com.x.common.utils.mail.SendEmailUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupervisionServiceImpl extends ServiceImpl<SupervisionDao, Supervision>
    implements SupervisionService {

  private final SupervisionDao supervisionDao;
  private final OperationDetailDao operationDetailDao;
  private final TLdapUserDao userDao;
  private final UploadLinkAddressDao uploadLinkAddressDao;
  private final TDictionariesDetailedDao tDictionariesDetailedDao;
  private final UserInformationUtil userInformationUtil;
  private final SupervisionUtil supervisionUtil;
  private final SendEmailUtil sendEmailUtil;
  private final OperationDetailService operationDetailService;

  private static final String YES = "yes";

  @Override
  public Page<SupervisionVo> list(SupervisionParam supervisionVo, String roleNames) {
    if (StringUtils.isEmpty(supervisionVo.getSupervisionType())) {
      throw new ServiceException("error", "观察类型不能为空");
    }
    RoleName roleName=new RoleName();
    // 用于匿名化处理，需要真实的角色名称
    String realRoleName = StringUtils.EMPTY;
    try {
      roleName.setRoleName(URLDecoder.decode(roleNames, "utf-8"));
      realRoleName = roleName.getRoleName();
    } catch (UnsupportedEncodingException e) {
      log.error("decode failed:{}", e.getMessage());
    }

    // 角色列表中包含大区观察员（纪检）的，给最大角色，否则就是普通大区员工
    if (StringUtils.contains(
            roleName.getRoleName(), UserInformationUtil.ROLE_NAME_REGIONAL_INSPECT)) {
      roleName = userInformationUtil.getRoleName(roleNames);
    } else {
      roleName.setRoleName(UserInformationUtil.ROLE_NAME_REGIONAL_STAFF);
    }
    supervisionVo.setRoleName(roleName.getRoleName());
    supervisionVo.setRoleNameDepartment(roleName.getRoleNameDepartment());
    if (StringUtils.isNotEmpty(roleName.getRoleNameDepartment())) {
      // 查询用户信息-部门id
      QueryWrapper<TLdapUser> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("usr_login", supervisionVo.getLdapId());
      TLdapUser tLdapUser = userDao.selectOne(queryWrapper);
      supervisionVo.setSubmitDepartmentId(tLdapUser.getUsrUdfDeptid());
    }
    Page<SupervisionVo> page = new Page<>(supervisionVo.getPageNum(), supervisionVo.getPageSize());
    page = supervisionDao.list(page, supervisionVo);

    // 匿名信息
    page.setRecords(
        supervisionUtil.anonymou(supervisionVo.getLdapId(), page.getRecords(), realRoleName));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    page.getRecords().stream().forEach(vo->{
      if (StringUtils.isEmpty(vo.getOperationName())){
        vo.setOperationName(StringUtils.EMPTY);
      }
      if (ObjectUtils.isNotEmpty(vo.getSubmitTime())){
        try {
          vo.setSubmitTimeString(formatter.format(vo.getSubmitTime()));
        }catch (Exception e){
          vo.setSubmitTimeString(StringUtils.EMPTY);
        }
      }
    });
    return page;
  }

  @Override
  public MySupervisionCountVo statisticalData(SupervisionParam supervisionVo, String roleNames) {
    if (StringUtils.isEmpty(supervisionVo.getSupervisionType())) {
      throw new ServiceException("error", "观察类型不能为空");
    }
    RoleName roleName=new RoleName();
    try {
      roleName.setRoleName(URLDecoder.decode(roleNames, "utf-8"));
    } catch (UnsupportedEncodingException e) {
      log.error("decode failed:{}", e.getMessage());
    }
    //特殊角色处理
    if (StringUtils.equals(roleName.getRoleName(),UserInformationUtil.ROLE_NAME_REGIONAL_AUDIT)
            || StringUtils.contains(roleName.getRoleName(),UserInformationUtil.ROLE_NAME_REGIONAL_BUSINESS)){
      roleName.setRoleName(UserInformationUtil.ROLE_NAME_REGIONAL_STAFF);
    } else {
      roleName = userInformationUtil.getRoleName(roleNames);
    }
    supervisionVo.setRoleName(roleName.getRoleName());
    supervisionVo.setRoleNameDepartment(roleName.getRoleNameDepartment());
    if (StringUtils.isNotEmpty(roleName.getRoleNameDepartment())) {
      // 查询用户信息-部门id
      QueryWrapper<TLdapUser> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("usr_login", supervisionVo.getLdapId());
      TLdapUser tLdapUser = userDao.selectOne(queryWrapper);
      supervisionVo.setSubmitDepartmentId(tLdapUser.getUsrUdfDeptid());
    }
    MySupervisionCountVo mySupervisionCountVo = supervisionDao.statisticalData(supervisionVo);
    return mySupervisionCountVo;
  }

  @Override
  public ResultVo findDetails(String id, String userId, String roleNames) {
    SupervisionDetailsVo supervisionDetailsvO = new SupervisionDetailsVo();
    // 基本信息
    Supervision supervision = supervisionDao.selectById(id);
    if (ObjectUtils.isEmpty(supervision)) {
      throw new ServiceException(ErrorCodeEnum.DATA_ERROR);
    }

    try {
      // 除大区观察员（纪检）角色外，其它角色都需要匿名提交人与提交部门
      String realRoleName = URLDecoder.decode(roleNames, "utf-8");
      if (!StringUtils.equalsIgnoreCase(supervision.getSubmitPeopleId(), userId)
              && !StringUtils.contains(
              realRoleName, UserInformationUtil.ROLE_NAME_REGIONAL_INSPECT)) {
        supervision.setSubmitPeople(null);
        supervision.setSubmitDepartment(null);
      }
    } catch (UnsupportedEncodingException e) {
      log.error("decode failed:{}", e.getMessage());
    }

    // 获取数据角色
    RoleName roleName = userInformationUtil.getRoleName(roleNames);
    // 匿名
    if (!StringUtils.equals(supervision.getSubmitPeopleId(), userId)
        && !StringUtils.equals(
            roleName.getRoleName(), UserInformationUtil.ROLE_NAME_REGIONAL_SUPERVISOR)) {
      supervision.setSubmitPeople(null);
      supervision.setSubmitDepartment(null);
    }

    // 优化部门展示格式
    supervision.setSubmitDepartment(UserInformationUtil.subString(supervision.getSubmitDepartment()));
    supervision.setContactedDepartment(UserInformationUtil.subString(supervision.getContactedDepartment()));
    supervisionDetailsvO.setSupervision(supervision);
    supervisionDetailsvO.setSupervision(supervision);
    // 附件信息
    QueryWrapper<UploadLinkAddress> upQueryWrapper = new QueryWrapper<>();
    upQueryWrapper.select().eq("uid", id);
    List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upQueryWrapper);
    supervisionDetailsvO.setUploadLinkAddress(uploadLinkAddresses);
    // 转办知会的流程信息限制
    List<OperationDetail> operationDetails =
        operationDetailDao.authority(
            id, userId, roleName.getRoleName(), roleName.getRoleNameDepartment());
    supervisionDetailsvO.setOperationDetail(operationDetails);
    return ResultVoUtil.success(supervisionDetailsvO);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void operation(OperationDetailVo operationDetailVo) {
    Supervision id =
        supervisionDao.selectOne(
            new QueryWrapper<Supervision>()
                .eq("id", operationDetailVo.getSupervisionId())
                .eq("status", SupervisionStatusEnum.STATUS_FINISH.getCode()));
    if (ObjectUtils.isNotEmpty(id)) {
      throw new ServiceException(ErrorCodeEnum.FINISH_NOT_OPERATE);
    }
    //判断是否为可以操作的流程段
    if (StringUtils.equals(operationDetailVo.getOperationName(),
            SupervisionStatusEnum.STATUS_FEEDBACK.getCode())){
      List<OperationDetail> competence =
              operationDetailDao.findCompetence
                      (operationDetailVo.getSupervisionId(), operationDetailVo.getOperationPeopleId());
      if (CollectionUtil.isEmpty(competence)) {
        throw new ServiceException(ErrorCodeEnum.NOT_COMPETENCE);
      }
    }
    // 驳回操作，通过后需要将状态改为待审批-----驳回后进入普通状态
    if (StringUtils.equals(
        operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_ADOPT.getCode())) {
      operationDetailVo.setOperationName(SupervisionStatusEnum.STATUS_PENDING.getCode());
    }
    // 审批操作，需要将状态改为待办理
    if (StringUtils.equals(
        operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_PENDING.getCode())) {
      operationDetailVo.setOperationName(SupervisionStatusEnum.STATUS_UNTREATED.getCode());
    }
    // 异步转办发邮件
    if (StringUtils.equals(
        operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_TRANSFER.getCode())) {
      sendEmailUtil.send(
          operationDetailVo.getByOperationPeopleIdList(),
          SupervisionStatusEnum.STATUS_TRANSFER.getCode(),
          SupervisionCodeEnum.MYSUPERVISION.getCode());
    }
    // 修改状态----知会操作不需要修改状态---除去知会操作
    if (!StringUtils.equals(
        operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_INFORM.getCode())) {
      Supervision supervision = new Supervision();
      supervision.setId(operationDetailVo.getSupervisionId());
      supervision.setStatus(operationDetailVo.getOperationName());
      supervision.setUpdateTime(new Date());
      if (StringUtils.equals(
          operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_FINISH.getCode())) {
        supervision.setProblemClueField(operationDetailVo.getProblemClueField());
        supervision.setFinishType(operationDetailVo.getFinishType());
      }
      supervisionDao.updateById(supervision);
    }

    if (StringUtils.equals(operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_INFORM.getCode())){
      Supervision supervision =
              supervisionDao.selectOne(
                      new QueryWrapper<Supervision>()
                              .eq("id", operationDetailVo.getSupervisionId()));
      supervision.setUpdateTime(new Date());
      supervisionDao.updateById(supervision);
    }

    // 区别被操作人多个的情况---新增关联表数据
    operationDetailService.add(operationDetailVo);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void add(SuAndUpVo suAndUpVo, String userId) {
    // 判断Userid
    if (userId.isEmpty()) {
      throw new ServiceException(ErrorCodeEnum.MISS_USERID);
    }
    Supervision supervision = new Supervision();
    // 生成单据编号---PS/DS+时间+四位自增数
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    StringBuffer stringBuffer = new StringBuffer();
    // 区别个人观察和部门观察的单据编号前缀
    if (StringUtils.equals(
        suAndUpVo.getSupervisionType(), SupervisionCodeEnum.SUPERVISION_TYPE_PERSONAL.getCode())) {
      stringBuffer.append("PS").append("_").append(sdf.format(new Date()));
    } else {
      stringBuffer.append("DS").append("_").append(sdf.format(new Date()));
    }

    // 查询上条订单的订单号
    QueryWrapper<Supervision> suQueryWrapper = new QueryWrapper<>();
    suQueryWrapper.eq("supervision_type", suAndUpVo.getSupervisionType());
    suQueryWrapper.orderByDesc("id");
    suQueryWrapper.last("limit 1");
    Supervision supervisionNew = supervisionDao.selectOne(suQueryWrapper);
    // 为空即第一条数据
    if (ObjectUtils.isEmpty(supervisionNew)) {
      stringBuffer.append("00001");
    } else {
      Integer number = Integer.valueOf(supervisionNew.getDocumentsNumber().substring(12, 16));
      // 编号到99999后初始化为1
      if (number >= 99999) {
        stringBuffer.append("00001");
      } else {
        number = number + 1;
        String format = String.format("%05d", number);
        stringBuffer.append(format);
      }
    }
    suAndUpVo.setDocumentsNumber(stringBuffer.toString());
    BeanUtils.copyProperties(suAndUpVo, supervision);

    // 根据观察类型确定初始状态
    supervision.setStatus(
        StringUtils.equals(
                supervision.getSupervisionType(),
                SupervisionCodeEnum.SUPERVISION_TYPE_PERSONAL.getCode())
            ? SupervisionStatusEnum.STATUS_UNTREATED.getCode()
            : SupervisionStatusEnum.STATUS_PENDING.getCode());

    // 查询角色
    QueryWrapper userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("usr_login", userId);
    TLdapUser user = userDao.selectOne(userQueryWrapper);
    supervision.setSubmitPeopleId(user.getUsrLogin());
    supervision.setSubmitPeople(user.getUsrName());
    supervisionDao.insert(supervision);

    if (!CollectionUtil.isEmpty(suAndUpVo.getFileId()) && suAndUpVo.getFileId().size() > 0) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", suAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(supervision.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(SuAndUpVo suAndUpVo) {
    Supervision supervision = new Supervision();
    BeanUtils.copyProperties(suAndUpVo, supervision);
    supervision.setUpdateTime(new Date());
    // 驳回状态修改状态
    if (StringUtils.equals(suAndUpVo.getStatus(), SupervisionStatusEnum.STATUS_REJECT.getCode())) {
      supervision.setStatus(SupervisionStatusEnum.STATUS_PENDING.getCode());
    }
    supervisionDao.updateById(supervision);

    if (!CollectionUtil.isEmpty(suAndUpVo.getFileId()) && suAndUpVo.getFileId().size() > 0) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", suAndUpVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(suAndUpVo.getId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  @Override
  public void excel(Class<SupervisionExcel> tClass, SupervisionParam supervisionVo, String roleNames)
      throws IOException {
    Field[] declaredFields = tClass.getDeclaredFields();
    List key = new ArrayList<>();
    List value = new ArrayList<>();
    Arrays.stream(declaredFields)
        .forEach(
            declaredField -> {
              ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
                key.add(annotation.value());
                value.add(declaredField.getName());
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
    String fileName = "supervise_" + "report" + "_" + formatTime;
    List<SupervisionVo> byExprotList = findByExprotList(supervisionVo, roleNames);
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
  public List<Map<String, Object>> createExcelRecord(List<SupervisionVo> supervisionList) {
    List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("sheetName", "sheet1");
    listmap.add(map);
    SupervisionVo supervision = new SupervisionVo();
    // 获得SimpleDateFormat类
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    QueryWrapper<TDictionariesDetailed> tdQueryWrapper = new QueryWrapper<>();
    for (int j = 0; j < supervisionList.size(); j++) {
      supervision = supervisionList.get(j);
      Map<String, Object> mapValue = new HashMap<String, Object>();
      mapValue.put("documentsNumber", supervision.getDocumentsNumber());
      SupervisionStatusEnum[] values = SupervisionStatusEnum.values();
      for (SupervisionStatusEnum value : values) {
        if (StringUtils.equals(supervision.getStatus(), value.getCode())) {
          mapValue.put("status", value.getName());
          break;
        }
      }
      mapValue.put("submitPeople", supervision.getSubmitPeople() == null
              ? StringUtils.EMPTY : supervision.getSubmitPeople());
      mapValue.put("submitDepartment", supervision.getSubmitDepartment() == null ? StringUtils.EMPTY
              : supervision.getSubmitDepartment());
      mapValue.put("submitTime", supervision.getSubmitTime() == null ? StringUtils.EMPTY
              : sf.format(supervision.getSubmitTime()));
      mapValue.put("contactedPeople",
          supervision.getContactedPeople() == null ? StringUtils.EMPTY : supervision.getContactedPeople());
      mapValue.put("contactedDepartment", supervision.getContactedDepartment() == null ? StringUtils.EMPTY
                      : supervision.getContactedDepartment());
      mapValue.put("cleanContent",
              supervision.getCleanContent() == null ? StringUtils.EMPTY : supervision.getCleanContent());
      mapValue.put("workContent",
              supervision.getWorkContent() == null ? StringUtils.EMPTY : supervision.getWorkContent());
      mapValue.put("riskContent",
              supervision.getRiskContent() == null ? StringUtils.EMPTY : supervision.getRiskContent());
      mapValue.put("advice", supervision.getAdvice() == null ? StringUtils.EMPTY : supervision.getAdvice());
      mapValue.put("anonymousType", supervision.getAnonymousType() == "yes" ? "是" : "否");
      if (StringUtils.isNotEmpty(supervision.getProblemClueField())) {
        // 查询字典--业务类别
        tdQueryWrapper = new QueryWrapper<>();
        tdQueryWrapper.eq("classification_id", "1");
        tdQueryWrapper.eq("type_code", supervision.getProblemClueField());
        TDictionariesDetailed problemClueField = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
        if (ObjectUtils.isNotEmpty(problemClueField)) {
          mapValue.put("problemClueField", problemClueField.getTypeName());
        } else {
          mapValue.put("problemClueField", StringUtils.EMPTY);
        }
      }
      if (StringUtils.isNotEmpty(supervision.getRiskLever())) {
        // 查询字典--风险等级
        tdQueryWrapper = new QueryWrapper<>();
        tdQueryWrapper.eq("classification_id", "7");
        tdQueryWrapper.eq("type_code",
            supervision.getRiskLever() == null ? StringUtils.EMPTY : supervision.getRiskLever());
        TDictionariesDetailed riskLever = tDictionariesDetailedDao.selectOne(tdQueryWrapper);
        if (ObjectUtils.isNotEmpty(riskLever)) {
          mapValue.put("riskLever", riskLever.getTypeName());
        } else {
          mapValue.put("riskLever", StringUtils.EMPTY);
        }
      }
      listmap.add(mapValue);
    }
    return listmap;
  }

  @Override
  public List<SupervisionVo> findByExprotList(SupervisionParam supervisionVo, String roleNames) {
    if (StringUtils.isEmpty(supervisionVo.getSupervisionType())) {
      throw new ServiceException("error", "观察类型不能为空");
    }
    // 用于匿名化处理，需要真实的角色名称
    String realRoleName = StringUtils.EMPTY;
    try {
      // 除大区观察员（纪检）角色外，其它角色都需要匿名提交人与提交部门
      realRoleName = URLDecoder.decode(roleNames, "utf-8");
    } catch (UnsupportedEncodingException e) {
      log.error("decode failed:{}", e.getMessage());
    }
    RoleName roleName = userInformationUtil.getRoleName(roleNames);
    supervisionVo.setRoleName(roleName.getRoleName());
    supervisionVo.setRoleNameDepartment(roleName.getRoleNameDepartment());
    if (StringUtils.isNotEmpty(roleName.getRoleNameDepartment())) {
      // 查询用户信息-部门id
      QueryWrapper<TLdapUser> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("usr_login", supervisionVo.getLdapId());
      TLdapUser tLdapUser = userDao.selectOne(queryWrapper);
      supervisionVo.setSubmitDepartmentId(tLdapUser.getUsrUdfDeptid());
    }
    List<SupervisionVo> list = supervisionDao.excelList(supervisionVo);
    // 匿名信息
    List<SupervisionVo> anonymou =
        supervisionUtil.anonymou(supervisionVo.getLdapId(), list, realRoleName);
    return anonymou;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void falseDelete(List<Long> asList) {
    UpdateWrapper<Supervision> updateWrapper =new UpdateWrapper<>();
    updateWrapper.in("id",asList);
    Supervision supervision = new Supervision();
    supervision.setDelType("Y");
    supervisionDao.update(supervision,updateWrapper);
  }

  @Override
  public List statisticsOne() {
    List<SupervisionStatisticsVo> list=supervisionDao.statisticsOne();
    Set<Integer> mlist = new HashSet<>();
    for (SupervisionStatisticsVo vo : list) {
      AtomicBoolean a= new AtomicBoolean(true);
      CommonServiceImpl.DEPT_MAP.keySet().stream().forEach(set->{
        if(vo.getSubmitDepartment().contains(set)&& a.get()){
          vo.setSubmitDepartment(CommonServiceImpl.DEPT_MAP.get(set));
          a.set(false);
        }
      });
    }
    //遍历找出重复公司名称
    for(int i = 0; i < list.size() - 1; i++){
      for(int j = i + 1; j < list.size(); j++){
        if(StringUtils.equals(list.get(i).getSubmitDepartment(),list.get(j).getSubmitDepartment())){
          list.get(i).setTotal(list.get(i).getTotal()+list.get(j).getTotal());
          mlist.add(j);
        }
      }
    }
    List<Integer> removeList = new ArrayList<>();
    mlist.stream().forEach(m->{
      removeList.add(m);
    });
    Collections.sort(removeList,Collections.reverseOrder());
    removeList.stream().forEach(m->{
      list.remove(list.get(m));
    });

    CommonServiceImpl.DEPT_MAP.keySet().stream()
            .forEach(
                    set -> {
                      boolean flag = true;
                      for (SupervisionStatisticsVo listNew : list) {
                        if (StringUtils.equals(
                                CommonServiceImpl.DEPT_MAP.get(set), listNew.getSubmitDepartment())) {
                          flag = false;
                        }
                      }
                      if (flag) {
                        SupervisionStatisticsVo vo = new SupervisionStatisticsVo();
                        vo.setSubmitDepartment(CommonServiceImpl.DEPT_MAP.get(set));
                        vo.setTotal(0);
                        list.add(vo);
                      }
                    });
    return list;
  }

  @Override
  public List statisticsTwo() {
    List<SupervisionStatisticsVo> list=supervisionDao.statisticsTwo();
    return list;
  }

  @Override
  public List statisticsThree() {
    List<SupervisionStatisticsVo> list=supervisionDao.statisticsThree();
    return list;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void top(String id, Integer topType) {
    supervisionDao.top(id,topType);
  }
}
