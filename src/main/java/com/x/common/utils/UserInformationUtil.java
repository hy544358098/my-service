package com.x.common.utils;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.x.common.enums.ErrorCodeEnum;
import com.x.common.enums.RoleEnum;
import com.x.common.enums.oa.SpecialStatusEnum;
import com.x.business.oa.model.entity.OaOperateEntity;
import com.x.business.independent.model.pojo.psp.PspBaseRes;
import com.x.business.independent.model.pojo.psp.PspUserInfo;
import com.x.business.independent.model.pojo.psp.PspUserReq;
import com.x.business.independent.model.pojo.psp.RoleInfoDTO;
import com.x.business.independent.model.vo.RoleName;
import com.x.business.independent.thirdservice.PspInfoService;
import com.x.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static com.x.common.constant.OaConstant.SEQ;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInformationUtil {

  /***
   * 角色名称-大区观察员
   */
  public static final String ROLE_NAME_REGIONAL_SUPERVISOR = "大区观察员";

  /***
   * 角色名称-大区观察员纪检
   */
  public static final String ROLE_NAME_REGIONAL_INSPECT = "大区观察员（纪检）";

  /***
   * 角色名称-部门观察员
   */
  public static final String ROLE_NAME_REGIONAL_DEPARTMENT = "部门负责人";

  /***
   * 角色名称-业务条线
   */
  public static final String ROLE_NAME_REGIONAL_BUSINESS = "业务条线";

  /***
   * 角色名称-审计
   */
  public static final String ROLE_NAME_REGIONAL_AUDIT = "大区观察员（审计）";

  /***
   * 角色名称-大区员工
   */
  public static final String ROLE_NAME_REGIONAL_STAFF = "大区员工";

  /***
   * 特殊角色
   */
  public static final String ROLE_NAME_SPECIAL = "特殊角色";


  private final PspInfoService pspInfoService;


  /***
   * @Description 获取用户角色
   * @Param roleNames
   * @return roleName
   */
  public RoleName getRoleName(String roleNames) {
    RoleName roleName = new RoleName();
    // 默认都是大区员工角色
    roleName.setRoleName(ROLE_NAME_REGIONAL_STAFF);
    //默认没有部门观察角色
    roleName.setRoleNameDepartment(null);
    if (StringUtils.isNotEmpty(roleNames)) {
      try {
        roleNames = URLDecoder.decode(roleNames, "utf-8");
      } catch (UnsupportedEncodingException e) {
        log.error("decode failed:{}", e.getMessage());
      }
      if (StringUtils.contains(roleNames, ROLE_NAME_REGIONAL_SUPERVISOR)) {
        roleName.setRoleName(ROLE_NAME_REGIONAL_SUPERVISOR);
      }
      if (StringUtils.contains(roleNames, ROLE_NAME_REGIONAL_DEPARTMENT)) {
        roleName.setRoleNameDepartment(ROLE_NAME_REGIONAL_DEPARTMENT);
      }
    }
    log.info("role name is : {}", roleName);
    return roleName;
  }

  /** 查询角色权限 */
  public PspUserInfo queryRole(String userId) {
    // 查询角色登录信息
    PspUserReq pspUserReq = new PspUserReq();
    pspUserReq.setLoginName(userId);
    PspBaseRes<PspUserInfo> userInfo = pspInfoService.getUserInfo(userId);
    PspUserInfo res = userInfo.getData();
    if (ObjectUtils.isEmpty(res)) {
      throw new ServiceException(ErrorCodeEnum.LOGIN_USER_NOT_EXIT);
    }
    return res;
  }

  /** 获取角色name---默认员工 */
  public String queryRoleName(PspUserInfo res) {
    String role = RoleEnum.STAFF.getName();
    List<String> roleList = new ArrayList<>();
    if (null==res.getRoles()){
      throw new ServiceException("error","角色信息为空");
    }
    for (RoleInfoDTO roleInfoDTO : res.getRoles()) {
      roleList.add(roleInfoDTO.getName());
      // 大区观察员
      if (StringUtils.equals(roleInfoDTO.getName(),RoleEnum.ADMIN.getName())) {
        role = RoleEnum.ADMIN.getName();
        break;
      }
      // 城市观察员
      if (StringUtils.equals(roleInfoDTO.getName(),RoleEnum.SUPERVISOR.getName())) {
        role = RoleEnum.SUPERVISOR.getName();
      }
    }
    return role;
  }

  /** 获取角色转办知会数据 */
  public List queryUidList(List<OaOperateEntity> tOaSpecialOperateEntities){

    // 处理同时知会并且转办的数据-按转办处理
    List list = new ArrayList<>();
    for (OaOperateEntity to : tOaSpecialOperateEntities) {
      for (OaOperateEntity toNew : tOaSpecialOperateEntities) {
        // 当同时有转办和知会数据时，按转办处理.判断条件：同一个重点观察数据&&不是同一条操作数据&&(to数据是转办||toNew是转办数据)
        if (StringUtils.equals(to.getUid(), toNew.getUid())
                && !to.getId().equals(toNew.getId())
                && (StringUtils.equals(
                to.getOperationName(), SpecialStatusEnum.STATUS_TRANSFER.getCode())
                || StringUtils.equals(
                toNew.getOperationName(), SpecialStatusEnum.STATUS_TRANSFER.getCode()))) {
          to.setOperationName(SpecialStatusEnum.STATUS_TRANSFER.getCode());
          toNew.setOperationName(SpecialStatusEnum.STATUS_TRANSFER.getCode());
        }
      }
      list.add(to.getUid());
    }
    return list;
  }

  /**
   * @Description 截取用户部门名称 @Date 2022/1/27 11:21
   *
   * @param str @Return {@link String}
   */
  public static String subString(String str) {
    if (!StringUtils.contains(str, SEQ)) {
      return str;
    }
    String substring = StringUtils.substringBetween(str,SEQ, SEQ);
    if (StringUtils.isEmpty(substring)) {
      substring = StringUtils.substringAfter(str, SEQ);
    }
    return substring;
  }
}
