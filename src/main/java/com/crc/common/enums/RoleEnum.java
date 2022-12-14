package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
  /** 大区观察员 */
  ADMIN("admin", "大区观察员"),
  /** 城市观察员 */
  SUPERVISOR("supervisor", "城市观察员"),
  /** 大区员工 */
  STAFF("staff", "大区员工");

  private String code;
  private String name;

  public static String getRoleCode(String roleNames) {
    for (RoleEnum roleEnum : RoleEnum.values()) {
      if ((roleEnum.getCode().equals(RoleEnum.ADMIN.getCode())
              || roleEnum.getCode().equals(RoleEnum.SUPERVISOR.getCode()))
          && roleNames.contains(roleEnum.getName())) {
        return roleEnum.getCode();
      }
    }
    return RoleEnum.STAFF.getCode();
  }
}
