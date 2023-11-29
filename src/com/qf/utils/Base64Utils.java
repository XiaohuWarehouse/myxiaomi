package com.qf.utils;

import java.util.Base64;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:05
 * description:
 */
public class Base64Utils {
    //base64编码
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //base64解码
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }
}
