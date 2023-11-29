package com.qf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/27 8:07
 * description:随机数工具类
 */
public class RandomUtils {
    //生成激活码
    public static String createActiveCode(){
        Random random=new Random();
        return getTime()+Integer.toHexString(random.nextInt(9999999));
    }
    public static String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
    //随机订单号
    public static String createOrderId(){
        return getTime()+new Random().nextInt(9999999);
    }
}
