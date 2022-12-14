package com.crc.business.independent.model.vo.result;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/26 9:59
 * @Version 1.0
 */
@Data
public class TLdUserCityVo {


    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("账户ID")
    private String usrLogin;

    @ApiModelProperty("密码")
    private String usrPassword;

    @ApiModelProperty("姓氏")
    private String usrLastName;

    @ApiModelProperty("姓拼音")
    private String usrUdfLastnamechn;

    @ApiModelProperty("名字")
    private String usrFirstName;

    @ApiModelProperty("名拼音")
    private String usrUdfFirstnameechn;

    @ApiModelProperty("姓名全称")
    private String usrName;

    @ApiModelProperty("显示名")
    private String usrDisplayName;

    @ApiModelProperty("身份证后四位")
    private String usrUdfNationalidlast4;

    @ApiModelProperty("邮箱")
    private String usrEmail;

    @ApiModelProperty("利润中心编码")
    private String usrUdfBuid;

    @ApiModelProperty("组织编号")
    private String usrUdfSetid;

    @ApiModelProperty("ldap标识")
    private String usrLdapGuid;

    @ApiModelProperty("员工编号")
    private String usrEmpNo;

    @ApiModelProperty("组织名称")
    private String actName;

    @ApiModelProperty("部门编号")
    private String usrUdfDeptid;

    @ApiModelProperty("手机号码")
    private String usrMobile;

    @ApiModelProperty("开始日期")
    private String usrStartDate;

    @ApiModelProperty("结束日期")
    private String usrEndDate;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty("是否启用")
    private Integer isEnable;

    @ApiModelProperty("城市公司")
    private String cityFirm;

}
