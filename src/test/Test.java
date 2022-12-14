import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @Description TODO @Author YANKAIBO @Date 2021/11/25 9:50 @Version 1.0 */
public class Test {
  public static void main(String[] args) {
    String sss = "一期78548.155，二期78548.155";
    getNumber(sss);
  }

  public static String getNumber(String str) {
    // 先判断有没有整数，如果没有整数那就肯定就没有小数
    Pattern p = Pattern.compile("(\\d+)");
    Matcher m = p.matcher(str);
    String result = StringUtils.EMPTY;
    if (m.find()) {
      Map<Integer, String> map = new TreeMap<>();
      Pattern p2 = Pattern.compile("(\\d+\\.\\d+)");
      m = p2.matcher(str);
      // 遍历小数部分
      while (m.find()) {
        result = m.group(1) == null ? "" : m.group(1);
        int i = str.indexOf(result);
        String s = str.substring(i, i + result.length());
        map.put(i, s);
        // 排除小数的整数部分和另一个整数相同的情况下，寻找整数位置出现错误的可能，还有就是寻找重复的小数
        str = str.substring(0, i) + str.substring(i + result.length());
      }
      // 遍历整数
      Pattern p3 = Pattern.compile("(\\d+)");
      m = p3.matcher(str);
      while (m.find()) {
        result = m.group(1) == null ? StringUtils.EMPTY : m.group(1);
        int i = str.indexOf(result);
        // 排除jia567.23.23在第一轮过滤之后留下来的jia.23对整数23产生干扰
        if (String.valueOf(str.charAt(i - 1)).equals(".")) {
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
    return result;
  }
}
