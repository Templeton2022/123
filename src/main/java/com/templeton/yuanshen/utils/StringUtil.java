package com.templeton.yuanshen.utils;

public class StringUtil {
    /**
     * 判断字符串是空
     * @param str
     * @return true: 字符串为空值
     */
    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串非空
     * @param str
     * @return true: 字符串非空
     */
    public static boolean isNotEmpty(String str) {
        if ((!"".equals(str)) && (str != null)) {
            return true;
        } else {
            return false;
        }
    }
}
