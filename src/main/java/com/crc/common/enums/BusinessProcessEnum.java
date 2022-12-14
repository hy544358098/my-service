package com.crc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BusinessProcessEnum
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/10/20 10:17
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
@Slf4j
public class BusinessProcessEnum {

    public enum OperationType {
        EQUAL(1, "="),
        MORE_THAN(2, ">"),
        MORE_THAN_EQUAL(3, ">="),
        LESS_THAN(4, "<"),
        LESS_THAN_EQUAL(5, "<="),
        INCLUDE(6, "包含"),
        NOT_INCLUDE(7, "不包含"),
        NOT_EQUAL(8, "不等于");

        OperationType(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        private Integer type;

        private String name;

        public Integer getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public static String getOperationType(Integer type) {
            for (BusinessProcessEnum.OperationType monitorStatus : BusinessProcessEnum.OperationType.values()) {
                if (monitorStatus.getType().equals(type)) {
                    return monitorStatus.getName();
                }
            }
            return "";
        }

        public static Map<Integer, String> toMapData() {
            Map<Integer, String> map = new HashMap<>(16);
            for (BusinessProcessEnum.OperationType monitorStatus : BusinessProcessEnum.OperationType.values()) {
                map.put(monitorStatus.getType(), monitorStatus.getName());
            }
            return map;
        }

        //TODO
        public static Boolean judgmentData(String data1, Integer condition, String data2) {
            if (StringUtils.isEmpty(data1) || StringUtils.isEmpty(data2)) {
                return false;
            }
            if (data1.endsWith("%") || data2.endsWith("%")) {
                data1 = data1.substring(0, data1.indexOf("%"));
                data2 = data2.substring(0, data2.indexOf("%"));
            }
            BigDecimal bigDecimal = null;
            BigDecimal bigDecimal1 = null;
            try {
                bigDecimal = new BigDecimal(data1);
                bigDecimal1 = new BigDecimal(data2);
            } catch (NumberFormatException exception) {
                log.error("传过来的值：{},参数设定的值：{}", data1, data2);
            } finally {
                try {
                    switch (condition) {
                        //等于
                        case 1:
                            if (StringUtils.equals(data1, data2)) {
                                return true;
                            }
                            break;
                        //大于
                        case 2:
                            if (null == bigDecimal || null == bigDecimal1) {
                                if (data1.compareTo(data2) > 0) {
                                    return true;
                                }
                            } else {
                                if (bigDecimal.compareTo(bigDecimal1) > 0) {
                                    return true;
                                }
                            }
                            break;
                        //大于等于
                        case 3:
                            if (null == bigDecimal || null == bigDecimal1) {
                                if (data1.compareTo(data2) >= 0) {
                                    return true;
                                }
                            } else {
                                if (bigDecimal.compareTo(bigDecimal1) >= 0) {
                                    return true;
                                }
                            }
                            break;
                        //小于
                        case 4:
                            if (null == bigDecimal || null == bigDecimal1) {
                                if (data1.compareTo(data2) < 0) {
                                    return true;
                                }
                            } else {
                                if (bigDecimal.compareTo(bigDecimal1) < 0) {
                                    return true;
                                }
                            }
                            break;
                        //小于等于
                        case 5:
                            if (null == bigDecimal || null == bigDecimal1) {
                                if (data1.compareTo(data2) <= 0) {
                                    return true;
                                }
                            } else {
                                if (bigDecimal.compareTo(bigDecimal1) <= 0) {
                                    return true;
                                }
                            }
                            break;
                        //包含
                        case 6:
                            if (data1.contains(data2)) {
                                return true;
                            }
                            break;
                        //不包含
                        case 7:
                            if (!(data1.contains(data2))) {
                                return true;
                            }
                            break;
                        //不等于
                        case 8:
                            if (!StringUtils.equals(data1, data2)) {
                                return true;
                            }
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    log.error("data type error:data1={},data2={}", data1, data2);
                    return false;
                }
                return false;
            }
        }
    }

    public enum ConditionType {
        STATUS_ATTENTION("special_attention", "特别关注"),
        STATUS_WARN("warning", "预警"),
        STATUS_NORMAL("transfer_limit_time", "转办时限"),
        STATUS_EXTRACT_PROPORTION("extract_proportion", "重点观察抽取比例");

        private String code;
        private String name;

        ConditionType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getConditionType(String code) {
            for (BusinessProcessEnum.ConditionType conditionType : BusinessProcessEnum.ConditionType.values()) {
                if (conditionType.getCode().equals(code)) {
                    return conditionType.getName();
                }
            }
            return "";
        }

        public static Map<String, String> toMapData() {
            Map<String, String> map = new HashMap<>(8);
            for (BusinessProcessEnum.ConditionType conditionType : BusinessProcessEnum.ConditionType.values()) {
                map.put(conditionType.getCode(), conditionType.getName());
            }
            return map;
        }
    }
}
