package com.x.business.oa.model.vo.request;

import java.io.Serializable;

/**
 * 描述: 用户vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-04-15 20:04
 */
public class DemoVo implements Serializable {

    private String userId;
    private String userKey;
    private String appToken;
    private String appId;
    private String appVersion;
    private String diviceId;
    private String diviceVersion;
    private String osVersion;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDiviceId() {
        return diviceId;
    }

    public void setDiviceId(String diviceId) {
        this.diviceId = diviceId;
    }

    public String getDiviceVersion() {
        return diviceVersion;
    }

    public void setDiviceVersion(String diviceVersion) {
        this.diviceVersion = diviceVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
