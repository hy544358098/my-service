package com.crc.runwork.core.ssdp.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crc.runwork.core.ssdp.entity.SsdpAppApiVo;
import com.crc.runwork.core.ssdp.entity.SsdpCloudApiVo;
import com.crc.runwork.core.ssdp.entity.SsdpSysApiVo;
import com.crc.runwork.core.utils.TranslateUtil;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: controller基类
 *
 * @outhor LUWEIMIAO1
 * @create 2019-09-05 15:17
 */
public class BaseController {

    /**
     * ssdp移动网关公共参数vo
     *
     * @param jsonObject
     * @return ssdp移动网关公共参数vo
     */
    protected SsdpAppApiVo getSsdpAppApiVo(JSONObject jsonObject) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("API_ATTRS").toJavaObject(SsdpAppApiVo.class);
    }

    /**
     * ssdp系统网关公共参数vo
     *
     * @param jsonObject
     * @return ssdp系统网关公共参数vo
     */
    protected SsdpSysApiVo geSsdpSysApiVo(JSONObject jsonObject) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("API_ATTRS").toJavaObject(SsdpSysApiVo.class);
    }

    /**
     * ssdp云网关公共参数vo
     *
     * @param jsonObject
     * @return ssdp云网关公共参数vo
     */
    protected SsdpCloudApiVo getSsdpCloudApiVo(JSONObject jsonObject) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("API_ATTRS").toJavaObject(SsdpCloudApiVo.class);
    }

    /**
     * 获取业务参数
     *
     * @param jsonObject
     * @return 业务参数JSONObject
     */
    protected JSONObject getBusData(JSONObject jsonObject) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("REQUEST_DATA").getJSONObject("BUS_DATA");
    }

    /**
     * 获取业务参数
     *
     * @param jsonObject
     * @return 业务参数JSONObject
     */
    protected JSONObject getRequestData(JSONObject jsonObject) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("REQUEST_DATA");
    }

    /**
     * 获取业务参数转业务Vo
     *
     * @param jsonObject
     * @param clazz      业务Vo class
     * @param <T>
     * @return 业务参数转业务Vo
     */
    protected <T> T getBusDataToJavaObject(JSONObject jsonObject, Class<T> clazz) {
        return jsonObject.getJSONObject("REQUEST").getJSONObject("REQUEST_DATA").getJSONObject("BUS_DATA").toJavaObject(clazz);
    }

    /**
     * 获取业务参数base64解码
     *
     * @param jsonObject
     * @return 业务参数JSONObject
     */
    protected JSONObject getBusDataBase64Decode(JSONObject jsonObject) {
        String busDataBase64EncodeStr = jsonObject.getJSONObject("REQUEST").getJSONObject("REQUEST_DATA").getString("BUS_DATA");
        String busDataBase64decodeStr = TranslateUtil.base64DdecodeToString(busDataBase64EncodeStr);
        return JSON.parseObject(busDataBase64decodeStr);
    }

    /**
     * 获取业务参数转业务Vo base64解码
     *
     * @param jsonObject
     * @param clazz      业务Vo class
     * @param <T>
     * @return 业务参数转业务Vo
     */
    @Primary
    public <T> T getBusDataBase64DecodeToJavaObject(JSONObject jsonObject, Class<T> clazz) {
        String busDataBase64EncodeStr = jsonObject.getJSONObject("REQUEST").getJSONObject("REQUEST_DATA").getString("BUS_DATA");
        String busDataBase64decodeStr = StringUtils.EMPTY;
        if (StringUtils.isEmpty(busDataBase64EncodeStr)) {
            busDataBase64decodeStr = "{}";
            return JSON.parseObject(busDataBase64decodeStr, clazz);
        }
        busDataBase64decodeStr = TranslateUtil.base64DdecodeToString(busDataBase64EncodeStr);
        return JSON.parseObject(busDataBase64decodeStr, clazz);
    }
}
