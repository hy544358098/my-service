package com.crc.business.independent.thirdservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.model.pojo.psp.*;
import com.crc.common.config.PspConfig;
import com.crc.common.enums.PspErrorCodeEnum;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * @Description PSP服务
 *
 * @author xiaoming
 * @date 2021/9/20 11:20
 */
@Service
@Slf4j
public class PspInfoService {
  /**
   * 获取用户权限标识
   */
  private static final String GET_USER_INFO = "user/getUserInfo";

  /**
   * 获取下级组织节点信息
   */
  private static final String GET_ORG_TREE = "org/getOrgTree";

  /**
   * 节点key值
   */
  public static final String CHILDREN = "children";
  public static final String DATA = "data";
  public static final String ORG_TYPE = "orgType";
  public static final String TYPE_THREE = "3";

  private final PspConfig pspConfig;

  public PspInfoService(PspConfig pspConfig) {
    this.pspConfig = pspConfig;
  }

  /***
   * @Description 获取用户权限信息
   * @Param
   * @return
   */
  public PspBaseRes<PspUserInfo> getUserInfo(String userName) {
    if (StringUtils.isEmpty(userName)) {
      throw new ServiceException(PspErrorCodeEnum.REQUEST_PARAMS_NULL);
    }
    String url = pspConfig.getUrl() + GET_USER_INFO;
    PspUserReq pspUserReq = new PspUserReq();
    pspUserReq.setLoginName(userName);
    JSONObject jsonObject =
            HttpClientUtils.pspHttpPost(url, (JSONObject) JSONObject.toJSON(pspUserReq));
    log.info("psp user info : {}", jsonObject);
    if (ObjectUtils.isEmpty(jsonObject)) {
      throw new ServiceException(PspErrorCodeEnum.RESULT_NULL);
    }
    PspUserInfo pspUserInfo =
            JSONObject.toJavaObject(jsonObject.getJSONObject(DATA), PspUserInfo.class);
    PspBaseRes<PspUserInfo> res = JSONObject.toJavaObject(jsonObject, PspBaseRes.class);
    pspUserInfo.setPassword(null);
    res.setData(pspUserInfo);
    if (res == null) {
      throw new ServiceException(PspErrorCodeEnum.RESULT_NULL);
    }
    return res;
  }

  /***
   * @Description 获取组织列表
   * @Param
   * @return
   */
  public PspBaseRes<OrgResDTO> getOrgTree(PspOrgTreeReq pspOrgTreeReq) {
    if (ObjectUtils.isEmpty(pspOrgTreeReq)) {
      throw new ServiceException(PspErrorCodeEnum.REQUEST_PARAMS_NULL);
    }
    String url = pspConfig.getUrl() + GET_ORG_TREE;
    JSONObject jsonObject =
            HttpClientUtils.pspHttpPost(url, (JSONObject) JSONObject.toJSON(pspOrgTreeReq));
    if (ObjectUtils.isEmpty(jsonObject)) {
      throw new ServiceException(PspErrorCodeEnum.RESULT_NULL);
    }
    delNode(jsonObject.getJSONArray(DATA), ORG_TYPE, TYPE_THREE);
    PspBaseRes<OrgResDTO> res = JSONObject.toJavaObject(jsonObject, PspBaseRes.class);
    if (res == null) {
      throw new ServiceException(PspErrorCodeEnum.RESULT_NULL);
    }
    return res;
  }

  /**
   * @Description 过滤掉组织类型为3，即项目的数据 @Date 2021/12/7 18:47
   *
   * @param body
   * @param key
   * @param value @Return
   */
  public static void delNode(JSONArray body, String key, String value) {
    Iterator<Object> o = body.iterator();
    while (o.hasNext()) {
      JSONObject jsonObject = (JSONObject) o.next();
      if (StringUtils.equals(jsonObject.getString(key), value)) {
        o.remove();
        continue;
      } else if (StringUtils.isNotEmpty(jsonObject.getString(CHILDREN))) {
        delNode(jsonObject.getJSONArray(CHILDREN), key, value);
      }
    }
  }
}
