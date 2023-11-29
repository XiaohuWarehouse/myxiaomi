package com.qf.utils;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:40
 * description:字符串工具类
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 ? true : false;
    }
}

