package com.qf.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 16:31
 * description:MD5加密
 */
public class MD5Utils {
    //加密
    public static String md5(String str){
        try {
            //1获取信息摘要
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes=str.getBytes("UTF-8");

            //自定算法
            for (int i = 0; i < bytes.length; i++) {
                bytes[i]+=3;
            }

            //2更新数据
            md5.update(bytes);
            //3加密
            byte[] digest = md5.digest();
            //4返回
            return new BigInteger(1,digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(md5("123"));
    }
}
