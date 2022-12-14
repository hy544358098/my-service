package com.crc.business.independent.model.pojo.psp;

import lombok.Data;

import java.util.List;

@Data
public class PspUserInfo {
    private String id;
    private String loginName;// 登录名
    private String password;// 密码
    private String no; // 工号
    private String userName; // 姓名
    private String telephone; // 电话
    private String mobile; // 手机
    private String eMail; // 邮箱
    private String userType;//用户类型

    // 归属组织
    private OrgResDTO org;
    private String parentNo;//上级编号
    private String parentName;//上级名称
    private String hrStatus;//是否在职 (A在职，I离职)
    private List<RoleInfoDTO> roles;
    private List<PositionDTO> positions;
}
