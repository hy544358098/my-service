package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.independent.model.vo.request.TLdapUserPO;
import com.crc.business.independent.model.vo.result.TLdUserCityVo;
import com.crc.business.independent.model.vo.result.TLdapUserVo;

import java.util.List;

/**
 * LDAP服务
 *
 * @author xiaoming
 * @since 2021-09-20
 */
public interface ITLdapUserService extends IService<TLdapUser> {

  /**
   * 查询单个用户信息
   *
   * @Date 2021/11/9 15:57
   * @param tLdapUserPO
   * @Return {@link TLdapUser}
   */
  TLdUserCityVo getUserInfo(TLdapUserPO tLdapUserPO);

  /**
   * 查询用户列表
   *
   * @Date 2021/11/9 15:57
   * @param tLdapUserPO
   * @param userId
   * @Return {@link List< TLdapUser>}
   */
  Page<TLdapUser> getUserList(TLdapUserPO tLdapUserPO, String userId);

  /**
   * 根据部门编码查询用户列表
   *
   * @Date 2021/11/9 15:57
   * @param tLdapUserPO
   * @Return {@link List< TLdapUserVo>}
   */
  List<TLdapUserVo> getUserByOrgCode(TLdapUserPO tLdapUserPO);
}
