package com.crc.runwork.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类
 *
 * @author LUWEIMIAO1
 */
public class TranslateUtil {

    private static final Logger logger = LoggerFactory.getLogger(TranslateUtil.class);

    private static String[] parsePatterns = {"yyyy-MM-dd HH:mm:ss"};

    /**
     * 毫秒日期转为字符串日期格式为yyyy-MM-dd HH:mm:ss
     *
     * @param strTimeMillis
     * @return String 字符串日期格式为yyyy-MM-dd HH:mm:ss
     */
    public static String formatTimeMillisToDate(String strTimeMillis) {
        if (StringUtils.isBlank(strTimeMillis)) {
            return TranslateUtil.getDate(new Date());
        }
        long ltimeMillis = 0L;
        try {
            ltimeMillis = Long.parseLong(strTimeMillis);
        } catch (Exception e) {
            logger.error("formatTimeMillisToDate parseLong error", e);
            return TranslateUtil.getDate(new Date());
        }
        Calendar bizCalendar = Calendar.getInstance();
        try {
            bizCalendar.setTimeInMillis(ltimeMillis);
            Calendar curCalendar = Calendar.getInstance();
            // 比较业务的年月日 大于当前系统的年月日 时，返回空
            if (bizCalendar.get(Calendar.YEAR) > curCalendar.get(Calendar.YEAR)
                    || bizCalendar.get(Calendar.MONTH) > curCalendar.get(Calendar.MONTH)
                    || bizCalendar.get(Calendar.DAY_OF_MONTH) > curCalendar.get(Calendar.DAY_OF_MONTH)) {
                return "";
            }
        } catch (Exception e) {
            logger.error("formatTimeMillisToDate deal error", e);
            return TranslateUtil.getDate(new Date());
        }

        return TranslateUtil.getDate(bizCalendar.getTime());
    }

    /**
     * 获取字符串日期格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date Date
     * @return String 字符串日期格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getDate(Date date) {
        return DateFormatUtils.format(date, TranslateUtil.parsePatterns[0]);
    }


    /**
     * base64编码
     *
     * @param source
     * @return
     */
    public static String base64DdecodeToString(String source) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        byte[] b = Base64.decodeBase64(source.getBytes());
        try {
            return new String(b, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("base64DdecodeToString error", e);
        }
        return "";
    }

    /**
     * base64解码
     *
     * @param source
     * @return
     */
    public static String base64encodeToString(String source) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        try {
            return Base64.encodeBase64String(source.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("base64encodeToString error", e);
        }
        return "";
    }

    public static String getTimeStamp() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
        return df.format(new Date());
    }

}
