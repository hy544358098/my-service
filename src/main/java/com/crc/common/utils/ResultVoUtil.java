package com.crc.common.utils;



import com.crc.business.independent.model.vo.result.ResultVo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Classname ResultVoUtil
 * @Date 2021/3/17 4:34 下午
 * @Created by wangxy
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        return ResultVo.builder()
                .code("0")
                .msg("成功")
                .data(object)
                .build();
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(String code,String msg){
        return ResultVo.builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static Object getObjDefault(Object obj) {
        // 得到类对象
        Class clz = obj.getClass();
        Field[] fs = clz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            // 设置些属性是可以访问的
            boolean isStatic = Modifier.isStatic(f.getModifiers());
            if(isStatic){
                continue;
            }
            // 设置些属性是可以访问的
            f.setAccessible(true);
            try {
                // 得到此属性的值
                Object val = f.get(obj);
                // 得到此属性的类型
                String type = f.getType().toString();
                if (type.endsWith("String") && val == null) {
                    // 给属性设值
                    f.set(obj, "");
                } else if ((type.endsWith("int") ||  type.endsWith("Integer") || type.endsWith("double")) && val == null) {
                    f.set(obj, 0);
                }else if ((type.endsWith("long")|| type.endsWith("Long") )&& val == null){
                    f.set(obj, 0L);
                } else if (type.endsWith("Date") && val == null) {
                    f.set(obj, Date.valueOf("1970-01-01"));
                }else if(type.endsWith("Timestamp") && val == null){
                    f.set(obj, Timestamp.valueOf("1970-01-01 00:00:00"));
                } else if (type.endsWith("BigDecimal") && val == null) {
                    f.set(obj, new BigDecimal(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

}
