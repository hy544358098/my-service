package com.crc.business.independent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crc.common.enums.SupervisionStatusEnum;
import com.crc.business.independent.dao.OperationDetailDao;
import com.crc.business.independent.dao.TLdapUserDao;
import com.crc.business.independent.dao.UploadLinkAddressDao;
import com.crc.business.independent.model.entity.OperationDetail;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.independent.model.vo.result.OperationDetailVo;
import com.crc.business.independent.service.OperationDetailService;
import com.crc.common.exception.OperationErrorCode;
import com.crc.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crc.common.enums.oa.SpecialStatusEnum.STATUS_INFORM;
import static com.crc.common.enums.oa.SpecialStatusEnum.STATUS_TRANSFER;

/** @author xiaoming */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationDetailServiceImpl extends ServiceImpl<OperationDetailDao, OperationDetail>
    implements OperationDetailService {

  private final OperationDetailDao operationDetailDao;
  private final TLdapUserDao tLdapUserDao;
  private final UploadLinkAddressDao uploadLinkAddressDao;



  /**
   * 转办操作
   *
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void add(OperationDetailVo operationDetailVo) {
    OperationDetail operationDetail = null;
    // 转办和知会
    if (StringUtils.equals(
            operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_TRANSFER.getCode())
        || StringUtils.equals(
            operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_INFORM.getCode())) {
      // 区别被操作人多个的情况
      List<String> byOperationPeopleIdList = operationDetailVo.getByOperationPeopleIdList();
      List<OperationDetail> operateEntityList = new ArrayList<>();
      for (String id : byOperationPeopleIdList) {
        // 已转办的数据不能再知会给同一人
        if (StringUtils.equals(
                operationDetailVo.getOperationName(), STATUS_INFORM.getCode())) {
          operateEntityList =
                  findOperateEntityByOperationPeopleId(
                          operationDetailVo.getSupervisionId(), STATUS_TRANSFER.getCode(), id);
          if (CollectionUtils.isNotEmpty(operateEntityList)) {
            log.error(
                    "already transfer data can not to inform  :{}",
                    operateEntityList.get(0).getByOperationPeopleId());
            throw new ServiceException(
                    OperationErrorCode.OPERATION_ERROR_0002,
                    operateEntityList.get(0).getByOperationPeople());
          }
        }

        // 插入被操作人信息
        operationDetailVo.setByOperationPeopleId(id);
        operationDetail = new OperationDetail();
        BeanUtils.copyProperties(operationDetailVo, operationDetail);
        // 新增关联表数据
        QueryWrapper<TLdapUser> tlWrapper = new QueryWrapper<>();
        tlWrapper.eq("usr_login", operationDetail.getByOperationPeopleId());
        TLdapUser tLdapUser = tLdapUserDao.selectOne(tlWrapper);
        operationDetail.setByOperationPeople(tLdapUser.getUsrName());
        operationDetail.setOperationName(operationDetail.getOperationName());
        operationDetail.setOperationTime(new Date());
        operationDetailDao.insert(operationDetail);
      }
    } else {
      // 其他状态
      operationDetail = new OperationDetail();
      BeanUtils.copyProperties(operationDetailVo, operationDetail);
      operationDetail.setOperationTime(new Date());
      operationDetailDao.insert(operationDetail);
    }
    // 处理反馈时的附件
    if (StringUtils.equals(
            operationDetailVo.getOperationName(), SupervisionStatusEnum.STATUS_FEEDBACK.getCode())
        && !operationDetailVo.getFileId().isEmpty()) {
      // 上传链接关联表添加关联观察表字段
      QueryWrapper<UploadLinkAddress> upWrapper = new QueryWrapper<>();
      upWrapper.in("id", operationDetailVo.getFileId());
      List<UploadLinkAddress> uploadLinkAddresses = uploadLinkAddressDao.selectList(upWrapper);
      for (UploadLinkAddress up : uploadLinkAddresses) {
        up.setUid(operationDetailVo.getSupervisionId().toString());
        uploadLinkAddressDao.updateById(up);
      }
    }
  }

  /**
   * 根据被操作人id、操作类型、操作数据id查询操作数据
   *
   * @param id
   * @param operationName
   * @param userId
   * @return
   */
  private List<OperationDetail> findOperateEntityByOperationPeopleId(
          Integer id, String operationName, String userId) {
    List<OperationDetail> operateEntity =
            operationDetailDao.selectList(
                    new QueryWrapper<OperationDetail>()
                            .eq("supervision_id", id)
                            .eq("operation_name", operationName)
                            .eq("by_operation_people_id", userId));
    return operateEntity;
  }
}
