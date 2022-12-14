package com.crc.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @Description 正则工具类 @Author YANKAIBO @Date 2021/11/25 10:33 @Version 1.0 */
@Slf4j
public class PatternUtils {
  private static final Pattern INTEGER_PATTERN = Pattern.compile("(\\d+)");
  private static final Pattern DECIMAL_PATTERN = Pattern.compile("(\\d+\\.\\d+)");
  private static final String[] UNIT_ARRAY = {"亿", "千万", "百万", "十万", "万", "千"};

  public static String getNumber(String param) {
    // 先替换掉字符串中的英文逗号
    String str = StringUtils.replace(param, ",", "");
    // 先判断有没有整数，如果没有整数那就肯定就没有小数
    Matcher matcher = INTEGER_PATTERN.matcher(str);
    String result = StringUtils.EMPTY;
    if (matcher.find()) {
      Map<Integer, String> map = new TreeMap<>();
      matcher = DECIMAL_PATTERN.matcher(str);
      // 遍历小数部分
      while (matcher.find()) {
        result = matcher.group(1) == null ? StringUtils.EMPTY : matcher.group(1);
        int i = str.indexOf(result);
        String s = str.substring(i, i + result.length());
        map.put(i, s);
        // 排除小数的整数部分和另一个整数相同的情况下，寻找整数位置出现错误的可能，还有就是寻找重复的小数
        str = str.substring(0, i) + str.substring(i + result.length());
      }
      // 遍历整数
      matcher = INTEGER_PATTERN.matcher(str);
      while (matcher.find()) {
        result = matcher.group(1) == null ? StringUtils.EMPTY : matcher.group(1);
        int i = str.indexOf(result);
        // 排除jia567.23.23在第一轮过滤之后留下来的jia.23对整数23产生干扰
        if (i > 0 && StringUtils.equals(String.valueOf(str.charAt(i - 1)), ".")) {
          // 将这个字符串删除
          str = str.substring(0, i - 1) + str.substring(i + result.length());
          continue;
        }
        String s = str.substring(i, i + result.length());
        map.put(i, s);
        str = str.substring(0, i) + str.substring(i + result.length());
      }
      result = StringUtils.EMPTY;
      for (Map.Entry<Integer, String> e : map.entrySet()) {
        result += e.getValue() + ",";
      }
      result = result.substring(0, result.length() - 1);
    } else {
      result = StringUtils.EMPTY;
    }
    // 处理包含单位的字符串
    result = handleUnitNumber(param, result);
    return result;
  }

  /**
   * @Description 处理包含单位的字符串 @Date 2022/1/17 18:32
   *
   * @param param 原始字符串
   * @param result 返回结果 @Return {@link String}
   */
  private static String handleUnitNumber(String param, String result) {
    String finalStr = param;
    if (StringUtils.isNotEmpty(result)
            && Arrays.stream(UNIT_ARRAY).anyMatch(unit -> StringUtils.contains(finalStr, unit))) {
      // 对于字符串中包含万，十万，百万，千万，亿等这种单位的汉字，需要乘以对应的单位数额。
      // 处理一个字符串包含多个数值的场景：一期18万,二期778.3万
      if (StringUtils.contains(result, ",")) {
        StringBuilder strResult = new StringBuilder();
        // 处理中文逗号隔开的关键数据：一期18万，二期778.3万
        if (StringUtils.contains(finalStr, "，")) {
          String[] splitStr = StringUtils.split(finalStr, "，");
          String[] splitNumber = StringUtils.split(result, ",");
          if (splitStr.length == splitNumber.length) {
            for (int i = 1; i <= splitStr.length; i++) {
              strResult.append(getUnitNumber(splitNumber[i - 1], splitStr[i - 1]));
              if (i != splitStr.length) {
                strResult.append(",");
              }
            }
          } else {
            log.error("包含非法字符：{}", finalStr);
          }
          // 处理英文逗号隔开的关键数据：一期18万,二期778.3万
        } else if (StringUtils.contains(finalStr, ",")) {
          String[] splitStr = StringUtils.split(finalStr, ",");
          String[] splitNumber = StringUtils.split(result, ",");
          if (splitStr.length == splitNumber.length) {
            for (int i = 1; i <= splitStr.length; i++) {
              strResult.append(getUnitNumber(splitNumber[i - 1], splitStr[i - 1]));
              if (i != splitStr.length) {
                strResult.append(",");
              }
            }
          } else {
            // 处理特殊场景下的数据：18,000,000,二期778.3万
            String replaceStr = StringUtils.replace(finalStr, ",000", "000");
            String[] splitStr1 = StringUtils.split(replaceStr, ",");
            String[] splitNumber1 = StringUtils.split(result, ",");
            if (splitStr1.length == splitNumber1.length) {
              for (int i = 1; i <= splitStr1.length; i++) {
                strResult.append(getUnitNumber(splitNumber1[i - 1], splitStr1[i - 1]));
                if (i != splitStr1.length) {
                  strResult.append(",");
                }
              }
            } else {
              log.error("包含非法字符：{}", replaceStr);
            }
          }
        } else {
          log.error("包含非法字符：{}", finalStr);
        }
        if (strResult.length() == 0) {
          strResult.append("0");
        }
        result = strResult.toString();
      } else {
        result = getUnitNumber(result, finalStr);
      }
    }
    return result;
  }

  /**
   * @Description 根据匹配到的单位计算对应数值 @Date 2022/1/17 17:04
   *
   * @param result 抽取出的数字
   * @param finalStr 原始字符串 @Return {@link String}
   */
  private static String getUnitNumber(String result, String finalStr) {
    BigDecimal number = new BigDecimal(result);
    if (StringUtils.contains(finalStr, "亿")) {
      result = number.multiply(new BigDecimal("100000000")).toString();
    } else if (StringUtils.contains(finalStr, "千万")) {
      result = number.multiply(new BigDecimal("10000000")).toString();
    } else if (StringUtils.contains(finalStr, "百万")) {
      result = number.multiply(new BigDecimal("1000000")).toString();
    } else if (StringUtils.contains(finalStr, "十万")) {
      result = number.multiply(new BigDecimal("100000")).toString();
    } else if (StringUtils.contains(finalStr, "万")) {
      result = number.multiply(new BigDecimal("10000")).toString();
    } else if (StringUtils.contains(finalStr, "千")) {
      result = number.multiply(new BigDecimal("1000")).toString();
    }
    return result;
  }

  /** 金额计算 */
  public static String money(String estimatedAmount) {
    String number = PatternUtils.getNumber(estimatedAmount);
    String[] numbers = number.split(",");
    BigDecimal money = new BigDecimal("0");
    for (String numberNew : numbers) {
      money = money.add(new BigDecimal(numberNew));
    }
    return money.toString();
  }

  public static String money(String estimatedAmount, String moneys) {
    String number = PatternUtils.getNumber(estimatedAmount);
    String[] numbers = number.split(",");
    BigDecimal money = new BigDecimal(moneys);
    for (String numberNew : numbers) {
      money = money.add(new BigDecimal(numberNew));
    }
    return money.toString();
  }

  public static void main(String[] args) {
    String sss = "18,000,000,二期778.3万";
    System.out.println(getNumber(sss));
  }
}
