package jie.flyer.common.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kain
 * @since 2019-11-27
 */
public class StringUtils {

    private static String[] starArr = {"*", "**", "***", "****", "*****", "******", "*******", "********", "*********", "**********"};

    public static String getStarChar(int length) {
        if (length <= 0) {
            return "";
        }
        if (length <= 10) {
            return starArr[length - 1];
        }
        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = '*';
        }
        return new String(arr);
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
            cs[0] -= 32;
        }
        return String.valueOf(cs);
    }


    public static boolean isEmpty(String str) {
        return str == null ? true : "".equals(str.trim());
    }


    public static <T> String join(Iterable<T> elements, String sep, final String leftEleStr, final String rightEleStr) {
        return org.apache.commons.lang3.StringUtils.join(Iterators.transform(elements.iterator(), from -> leftEleStr + from + rightEleStr), sep);
    }

    public static <T> String join(Iterable<T> elements, String sep, final String eleStr) {
        return join(elements, sep, eleStr, eleStr);
    }

    public static <T> String join(Iterable<T> elements, String sep) {
        if (elements == null || Iterables.isEmpty(elements)) {
            return "";
        }
        return join(elements, sep, "", "");
    }

    public static String startsWith(String str, String sep) {
        if (str == null) {
            return null;
        }
        return str.startsWith(sep) ? str : sep + str;
    }

    public static String[] split(String str, String sep) {
        if (isEmpty(str)) {
            return new String[0];
        }
        return str.split(sep);
    }

    public static boolean isBlank(CharSequence s) {
        return org.apache.commons.lang3.StringUtils.isBlank(s);
    }

    public static String hump(String str) {
        return delSplitAndUpCaseFirst(str, "_");
    }

    /**
     * 将驼峰改成下划线
     *
     * @param str
     * @return
     */
    public static String underLine(String str) {
        StringBuilder newAttr = new StringBuilder();
        boolean isFirstChar = true;
        for (char c : str.toCharArray()) {
            newAttr.append(Character.isUpperCase(c) ?
                    (isFirstChar ? Character.toLowerCase(c) : "_" + Character.toLowerCase(c)) : c);
            isFirstChar = false;
        }
        return newAttr.toString();
    }

    /**
     * 将下划线改成驼峰
     *
     * @param str
     * @return
     */
    public static String camelHump(String str) {
        StringBuilder result = new StringBuilder();
        if ((str == null) || (str.isEmpty())) {
            return "";
        }
        if (!str.contains("_")) {
            return str;
        }
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ("_".charAt(0) == ch) {
                flag = true;
            } else {
                if (flag) {
                    result.append(Character.toUpperCase(ch));
                    flag = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static String delSplitAndUpCaseFirst(String str, String split) {
        String param = split + "[a-z|A-Z]";
        Pattern pattern = Pattern.compile(param);
        StringBuffer sb = new StringBuffer();
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            m.appendReplacement(sb, m.group().toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString().replace(split, "");
    }

    /**
     * 转换到半角字符串，并去除无用字符（空格，换行，制表符等）
     *
     * @param originStr 原始字符串
     * @return 半角字符串
     */
    public static String convertToHalfWidth(String originStr) {
        if (isBlank(originStr)) {
            return originStr;
        }
        char[] charArray = originStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray).replaceAll("\\s*", "");
    }
}
