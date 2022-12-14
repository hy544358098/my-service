package com.crc.business.oa.model.pojo;

import com.crc.common.enums.oa.OaBusinessCodeEnum;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 * @author YANKAIBO
 */
@Data
public class OaResultData implements Serializable {
    private static final String SUCCESS = "succeed";

    private static final String ERROR = "error";

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 无数据的返回参数
     *
     * @return
     */
    public static OaResultData success() {
        OaResultData resultData = new OaResultData();
        resultData.setMessage(SUCCESS);
        resultData.setCode("200");
        return resultData;
    }

    public static OaResultData success(String code,String message){
        OaResultData resultData = new OaResultData();
        resultData.setMessage(message);
        resultData.setCode(code);
        return resultData;
    }


    /**
     * 有数据的返回参数
     *
     * @param t
     * @return
     */
    public static OaResultData success(T t) {
        OaResultData resultData = new OaResultData();
        resultData.setMessage(SUCCESS);
        resultData.setCode("200");
        resultData.setData(t);
        return resultData;
    }

    public static OaResultData error() {
        OaResultData resultData = new OaResultData();
        resultData.setMessage(ERROR);
        resultData.setCode("201");
        return resultData;
    }

    public static OaResultData error(String code, String message) {
        OaResultData resultData = new OaResultData();
        resultData.setMessage(message);
        resultData.setCode(code);
        return resultData;
    }

    public static OaResultData error(OaBusinessCodeEnum oaBusinessCodeEnum) {
        OaResultData resultData = new OaResultData();
        resultData.setMessage(oaBusinessCodeEnum.getMsg());
        resultData.setCode(oaBusinessCodeEnum.getCode());
        return resultData;
    }

}
