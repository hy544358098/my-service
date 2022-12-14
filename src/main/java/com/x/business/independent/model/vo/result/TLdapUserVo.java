package com.x.business.independent.model.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TLdapUserVo {

  @ApiModelProperty("账户ID")
  private String usrLogin;

  @ApiModelProperty("用户姓名")
  private String usrName;

  @ApiModelProperty("组织名称")
  private String actName;
}
