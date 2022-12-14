package com.crc.common.utils.mail;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crc.business.independent.dao.OperationDetailDao;
import com.crc.business.independent.dao.SettingParameterDao;
import com.crc.business.independent.dao.TDictionariesDetailedDao;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.model.entity.OperationDetail;
import com.crc.business.independent.model.entity.SettingParameter;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.oa.dao.OaOperateDao;
import com.crc.business.oa.model.entity.OaOperateEntity;
import com.crc.business.oa.service.impl.OperationServiceImpl;
import com.crc.common.config.AsyncConfig;
import com.crc.common.enums.BusinessProcessEnum;
import com.crc.common.enums.SupervisionCodeEnum;
import com.crc.common.enums.oa.SpecialStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @Description 邮件发送工具类 @Author xiaoming @Date 2021/10/26 13:49 @Version 1.0 */
@Configuration
@EnableScheduling
@Slf4j
public class SendEmailUtil {

  @Autowired private TLdapUserDao userDao;
  @Autowired private OaOperateDao operateDao;
  @Autowired private SettingParameterDao settingParameterDao;
  @Autowired private TDictionariesDetailedDao tDictionariesDetailedDao;
  @Autowired private OperationDetailDao operationDetailDao;
  @Autowired private AsyncConfig asyncConfig;
  @Autowired private OperationServiceImpl operationService;

  private static final String REMIND = "remind";

  public void send(List<String> userId, String type, String code) {
    TDictionariesDetailed tDictionariesDetailed =
        tDictionariesDetailedDao.selectOne(
            new QueryWrapper<TDictionariesDetailed>()
                .eq("classification_id", "10")
                .eq("type_code", code));
    List<String> emailList = new ArrayList<>();
    QueryWrapper<TLdapUser> user;
    for (String byOperationPeopleId : userId) {
      user = new QueryWrapper<>();
      user.eq("usr_login", byOperationPeopleId);
      TLdapUser tLdapUser = userDao.selectOne(user);
      emailList.add(tLdapUser.getUsrEmail());
    }
    asyncConfig.sendEmail(tDictionariesDetailed, emailList, type);
  }

  /*@Scheduled(cron = "${mail.schedules}")*/
  public void timingRemind() throws ParseException {
    log.info("定时任务开始");
    // 所有转办数据
    List<OaOperateEntity> specialOperateEntities = operateDao.timingRemind();
    List<OperationDetail> operationDetails = operationDetailDao.timingRemind();
    // 参数列表中转办
    List<SettingParameter> type =
        settingParameterDao.selectList(
            new QueryWrapper<SettingParameter>()
                .eq("type", BusinessProcessEnum.ConditionType.STATUS_NORMAL.getCode()));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<String> userIdList = new ArrayList<>();
    Date newDate = sdf.parse(sdf.format(new Date()));
    for (SettingParameter settingParameter : type) {
      // 字典
      TDictionariesDetailed id =
          tDictionariesDetailedDao.selectOne(
              new QueryWrapper<TDictionariesDetailed>()
                  .eq("id", settingParameter.getDictionaryId()));
      Integer time = settingParameter.getExpireTime() - settingParameter.getReminderTime();
      for (OaOperateEntity specialOperateEntity : specialOperateEntities) {
        // 判断数据是否为已转办状态
        String tableName = operationService.findTableName(specialOperateEntity.getProcessType());
        String status = operateDao.findStatus(specialOperateEntity.getUid(), tableName);
        if (!StringUtils.equals(SpecialStatusEnum.STATUS_TRANSFER.getCode(), status)) {
          continue;
        }
        // 验证是否有人对这条数据有反馈
        String operation = operateDao.findOperationTime(specialOperateEntity);
        if (StringUtils.equals(id.getTypeCode(), specialOperateEntity.getProcessType())
            && StringUtils.isEmpty(operation)) {
          // 需提醒的时间
          Date operationTime = null;
          try {
            operationTime = sdf.parse(sdf.format(specialOperateEntity.getOperationTime()));
          } catch (Exception e) {
            log.error("时间转换异常");
          }
          long timeNew = (newDate.getTime() - operationTime.getTime()) / (60 * 60 * 24 * 1000);
          if (timeNew == time) {
            userIdList = new ArrayList<>();
            userIdList.add(specialOperateEntity.getByOperationPeopleId());
            send(userIdList, REMIND, specialOperateEntity.getProcessType());
            log.info("到期的重点观察操作表id：{}", specialOperateEntity.getId());
          }
        }
      }
      for (OperationDetail operationDetail : operationDetails) {
        String operation = operationDetailDao.findOperationTime(operationDetail);
        if (StringUtils.equals(id.getTypeCode(), SupervisionCodeEnum.MYSUPERVISION.getCode())
            && StringUtils.isEmpty(operation)) {
          // 需提醒的时间
          Date operationTime = null;
          try {
            operationTime = sdf.parse(sdf.format(operationDetail.getOperationTime()));
          } catch (Exception e) {
            log.error("时间转换异常");
          }
          long timeNew = (newDate.getTime() - operationTime.getTime()) / (60 * 60 * 24 * 1000);
          if (timeNew == time) {
            userIdList = new ArrayList<>();
            userIdList.add(operationDetail.getByOperationPeopleId());
            send(userIdList, REMIND, SupervisionCodeEnum.MYSUPERVISION.getCode());
            log.info("到期的我要观察操作表id：{}", operationDetail.getId());
          }
        }
      }
    }
    log.info("定时任务结束");
  }
}
