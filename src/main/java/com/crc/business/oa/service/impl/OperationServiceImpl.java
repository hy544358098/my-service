package com.crc.business.oa.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.model.entity.OperationDetail;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.oa.dao.OaOperateDao;
import com.crc.business.oa.dao.SpecialOperateDao;
import com.crc.business.oa.model.entity.OaOperateEntity;
import com.crc.business.oa.model.vo.request.OperationParam;
import com.crc.business.oa.service.OperationService;
import com.crc.common.config.AsyncConfig;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.enums.OaTableNameEnum;
import com.crc.common.enums.SupervisionStatusEnum;
import com.crc.common.enums.oa.OaCommonStatusEnum;
import com.crc.common.exception.OperationErrorCode;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.mail.SendEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crc.common.enums.oa.SpecialStatusEnum.*;

/** @Description: 操作实现类 @Author: xiaoming @Date: 2021/10/13 11:44 @Version: 1.0 */
@Service
@Slf4j
public class OperationServiceImpl extends ServiceImpl<SpecialOperateDao, OaOperateEntity>
        implements OperationService {
  @Autowired private TLdapUserDao tLdapUserDao;
  @Autowired private SpecialOperateDao oaSpecialOperateDao;
  @Autowired private SendEmailUtil sendEmailUtil;
  @Autowired private OaOperateDao oaOperateDao;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveOperationData(OperationParam purchaseOperationParam) throws ServiceException {
    List<OaOperateEntity> list = new ArrayList<>();
    saveOperation(purchaseOperationParam, list);
  }



  /**
   * 批量保存操作数据
   *
   * @param purchaseOperationParam
   * @param list
   */
  private void saveOperation(
          OperationParam purchaseOperationParam, List<OaOperateEntity> list)
          throws ServiceException {
    //判断是否为可以操作的反馈
    if (StringUtils.equals(purchaseOperationParam.getOperationName(),
            SupervisionStatusEnum.STATUS_FEEDBACK.getCode())){
      List<OaOperateEntity> competence =
              oaOperateDao.findCompetence
                      (purchaseOperationParam.getSupervisionId(), purchaseOperationParam.getOperationPeopleId());
      if (CollectionUtil.isEmpty(competence)) {
        throw new ServiceException(ErrorCodeEnum.NOT_COMPETENCE);
      }
    }

    if (StringUtils.equalsAny(
            purchaseOperationParam.getOperationName(),
            STATUS_FINISH.getCode(),
            STATUS_WARN.getCode(),
            STATUS_FEEDBACK.getCode())) {
      addSpecialOperationToList(purchaseOperationParam, list, null);
    } else {
      List<TLdapUser> tLdapUsers =
              tLdapUserDao.selectList(
                      new QueryWrapper<TLdapUser>()
                              .in("usr_login", purchaseOperationParam.getByOperationPeopleIdList()));
      tLdapUsers.stream()
              .forEach(
                      tLdapUser -> {
                        // 防止操作人与被操作人是同一人
                        if (StringUtils.equals(
                                tLdapUser.getUsrLogin(), purchaseOperationParam.getOperationPeopleId())) {
                          log.error(
                                  "operation and beOperation is the same person :{},{}",
                                  tLdapUser.getUsrLogin(),
                                  purchaseOperationParam.getOperationPeopleId());
                          throw new ServiceException(
                                  OperationErrorCode.OPERATION_ERROR_0004,
                                  purchaseOperationParam.getOperationPeople());
                        }
                        addSpecialOperationToList(purchaseOperationParam, list, tLdapUser);
                      });
    }
    // 转办发邮件
    if (StringUtils.equals(
            purchaseOperationParam.getOperationName(),
            SupervisionStatusEnum.STATUS_TRANSFER.getCode())) {
      sendEmailUtil.send(
              purchaseOperationParam.getByOperationPeopleIdList(),
              SupervisionStatusEnum.STATUS_TRANSFER.getCode(),
              purchaseOperationParam.getProcessType());
    }
    if (CollectionUtil.isNotEmpty(list)) {
      saveBatch(list);
    }
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void systemOperation(OaOperateEntity operationParam) throws ServiceException {
    operationParam.setOperationName(OaCommonStatusEnum.MonitorStatus.STATUS_WARN.getCode());
    operationParam.setOperationPeople("系统");
    save(operationParam);
  }

  /**
   * 根据被操作人id、操作类型、操作数据uid查询操作数据
   *
   * @param uid
   * @param operationName
   * @param userId
   * @return
   */
  private List<OaOperateEntity> findOperateEntityByOperationPeopleId(
          String uid, String operationName, String userId) {
    List<OaOperateEntity> operateEntity =
            oaSpecialOperateDao.selectList(
                    new QueryWrapper<OaOperateEntity>()
                            .eq("uid", uid)
                            .eq("operation_name", operationName)
                            .eq("by_operation_people_id", userId));
    return operateEntity;
  }

  /**
   * 将操作实体存入list中批量插入
   *
   * @param purchaseOperationParam
   * @param list
   * @param tLdapUser
   */
  private void addSpecialOperationToList(
          OperationParam purchaseOperationParam,
          List<OaOperateEntity> list,
          TLdapUser tLdapUser) {
    List<OaOperateEntity> operateEntityList = new ArrayList<>();
    // 防止已办结的数据被状态回滚
    operateEntityList =
            findOperateEntityByOperationPeopleId(
                    purchaseOperationParam.getSupervisionId(), STATUS_FINISH.getCode(), StringUtils.EMPTY);
    if (CollectionUtils.isNotEmpty(operateEntityList)) {
      log.error(
              "this process is already finished :{}", operateEntityList.get(0).getOperationName());
      throw new ServiceException(OperationErrorCode.OPERATION_ERROR_0001, StringUtils.EMPTY);
    }
    if (StringUtils.equals(purchaseOperationParam.getOperationName(), STATUS_INFORM.getCode())) {
      // 已转办的数据不能再知会给同一人
      operateEntityList =
              findOperateEntityByOperationPeopleId(
                      purchaseOperationParam.getSupervisionId(),
                      STATUS_TRANSFER.getCode(),
                      tLdapUser.getUsrLogin());
      if (CollectionUtils.isNotEmpty(operateEntityList)) {
        log.error(
                "already transfer data can not to inform  :{}",
                operateEntityList.get(0).getByOperationPeopleId());
        throw new ServiceException(
                OperationErrorCode.OPERATION_ERROR_0002,
                operateEntityList.get(0).getByOperationPeople());
      }
      // 防止同一数据重复知会给同一人
      operateEntityList =
              findOperateEntityByOperationPeopleId(
                      purchaseOperationParam.getSupervisionId(),
                      STATUS_INFORM.getCode(),
                      tLdapUser.getUsrLogin());
      if (CollectionUtils.isNotEmpty(operateEntityList)) {
        log.error("repeat inform to :{}", operateEntityList.get(0).getByOperationPeopleId());
        throw new ServiceException(
                OperationErrorCode.OPERATION_ERROR_0003,
                operateEntityList.get(0).getByOperationPeople());
      }
    }
    OaOperateEntity specialOperateEntity = new OaOperateEntity();
    BeanUtils.copyProperties(purchaseOperationParam, specialOperateEntity);
    specialOperateEntity.setUid(purchaseOperationParam.getSupervisionId());
    specialOperateEntity.setByOperationPeopleId(null != tLdapUser ? tLdapUser.getUsrLogin() : "");
    specialOperateEntity.setByOperationPeople(null != tLdapUser ? tLdapUser.getUsrName() : "");
    specialOperateEntity.setOperationTime(new Date());
    list.add(specialOperateEntity);
  }

  /**
   * 根据操作类型返回uid表名
   *
   * @param processType
   */
  public String findTableName(String processType) {
    OaTableNameEnum[] values = OaTableNameEnum.values();
    String tableName = null;
    for (OaTableNameEnum value : values) {
      if (StringUtils.equals(processType, value.getName())) {
        tableName = value.getTable();
      }
    }
    return tableName;
  }
}
