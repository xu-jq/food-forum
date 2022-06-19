package com.xu.util;


import java.util.Random;
import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        Random random = new Random();
        String id = random.nextInt(100000)+"";

        return id;
    }

    /*public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
    }*/


}

