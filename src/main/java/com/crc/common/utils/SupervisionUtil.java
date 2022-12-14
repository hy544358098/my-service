package com.crc.common.utils;

import com.crc.business.independent.model.vo.RoleName;
import com.crc.business.independent.model.vo.result.SupervisionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupervisionUtil {

  private static final String YES = "yes";

  /** 匿名置空 本人或大区观察管理员*/
  public List<SupervisionVo> anonymou(String userId, List<SupervisionVo> records, String roleName) {
    for (SupervisionVo supervision : records) {
      // 优化部门展示格式
      supervision.setSubmitDepartment(UserInformationUtil.subString(supervision.getSubmitDepartment()));
      supervision.setContactedDepartment(UserInformationUtil.subString(supervision.getContactedDepartment()));
      if (!StringUtils.equalsIgnoreCase(supervision.getSubmitPeopleId(), userId)
              && !StringUtils.contains(
              roleName, UserInformationUtil.ROLE_NAME_REGIONAL_INSPECT)) {
        supervision.setSubmitPeople(null);
        supervision.setSubmitDepartment(null);
      }
    }

    return records;
  }
}
