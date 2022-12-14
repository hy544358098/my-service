package com.x.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 *
 * @author xiaoming
 * @date 2021-09-17 13:45:52
 */
@Data
@TableName("t_ldap_user")
public class TLdapUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 账户ID
	 */
	private String usrLogin;
	/**
	 * 密码
	 */
	private String usrPassword;
	/**
	 * 姓氏
	 */
	private String usrLastName;
	/**
	 * 姓拼音
	 */
	private String usrUdfLastnamechn;
	/**
	 * 名字
	 */
	private String usrFirstName;
	/**
	 * 名拼音
	 */
	private String usrUdfFirstnameechn;
	/**
	 * 姓名全称
	 */
	private String usrName;
	/**
	 * 显示名
	 */
	private String usrDisplayName;
	/**
	 * 身份证后四位
	 */
	private String usrUdfNationalidlast4;
	/**
	 * 邮箱
	 */
	private String usrEmail;
	/**
	 * 利润中心编码
	 */
	private String usrUdfBuid;
	/**
	 * 组织编号
	 */
	private String usrUdfSetid;
	/**
	 * ldap标识
	 */
	private String usrLdapGuid;
	/**
	 * 员工编号
	 */
	private String usrEmpNo;
	/**
	 * 组织名称
	 */
	private String actName;
	/**
	 * 部门编号
	 */
	private String usrUdfDeptid;
	/**
	 * 手机号码
	 */
	private String usrMobile;
	/**
	 * 开始日期
	 */
	private String usrStartDate;
	/**
	 * 结束日期
	 */
	private String usrEndDate;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;
	/**
	 * 是否启用，1启用，0禁用
	 */
	private Integer isEnable;

}
