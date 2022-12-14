package com.x.business.independent.model.pojo.psp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
public class MenuDO{
  private String id;
  private String parentId;
  private String applicationId;
  private String name;
  private String href;
  private BigDecimal sort;
  private String isShow;
  private String permission;
  private String type;
  private String remarks;
  private String delFlag;
  private Integer level;
  private List<MenuDO> children;
}
