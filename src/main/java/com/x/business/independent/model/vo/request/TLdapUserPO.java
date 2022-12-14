package com.x.business.independent.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TLdapUserPO {

  @ApiModelProperty("账户ID")
  private String usrLogin;

  @ApiModelProperty("用户姓名")
  private String usrName;

  @ApiModelProperty("部门编码")
  private String orgCode;

  @ApiModelProperty("每页数据")
  private Integer pageSize = 10;

  @ApiModelProperty("页大小")
  private Integer pageNum = 1;
}
