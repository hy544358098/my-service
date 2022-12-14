package com.crc.runwork.core.ssdp;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.crc.runwork.core.utils.TranslateUtil;

/**
 * 返回ssdp 响应参数对象
 */
public class ResponseData {
    /** 响应码 */
    private String returnCode;

    /** 描述 */
    private String returnDesc;

    /** 时间戳 */
    private String returnStamp =  TranslateUtil.getTimeStamp();

    /** 业务数据对象 */
    private Object returnData = null;

    public ResponseData() {}

    public ResponseData(String returnCode, String returnDesc) {
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
    }

    public ResponseData(String returnCode, String returnDesc, Object returnData) {
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
        this.returnData = returnData;
    }

    public static ResponseData success() {
        return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC, "");
    }

    @JSONField(name = "RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @JSONField(name = "RETURN_DESC")
    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    @JSONField(name = "RETURN_STAMP")
    public String getReturnStamp() {
        return returnStamp;
    }

    public void setReturnStamp(String returnStamp) {
        this.returnStamp = returnStamp;
    }

    @JSONField(name = "RETURN_DATA")
    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    @Override
    public String toString() {
        JSONObject reustObj = new JSONObject();
        reustObj.put("RESPONSE", this);
        return reustObj.toJSONString();
    }

}
