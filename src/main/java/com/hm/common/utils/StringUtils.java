package com.hm.common.utils;

/**
 * Created by hmaccelerate on 2015/6/23.
 */
public class StringUtils {
    public static boolean isEmpty(String text) {
        return text == null || text.trim().equals("") ? true : false;
    }
}
