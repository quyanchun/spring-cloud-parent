package com.yanchun.user.controller;

import com.yanchun.common.utils.MD5;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author quyanchun
 * @Date 2018/12/7
 */
public class test {
    public static void main(String[] args) {

//        long time = new Date().getTime();
//        long l = time / 1000;
        String encode = MD5.encode("7");
        System.out.println(encode);
//        String s = "asdadadsada,eeeewaaa111,,,,a,,";
//        String s1 = removeNull(s);
//        System.out.println(s1);

    }

    public static String removeNull(String s) {
        String[] strings = null;
        if (s == null || s.length() < 1)
            return null;
        strings = s.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        if (strings == null || strings.length < 1)
            return null;
        if (strings.length == 1) {
            stringBuffer.append(strings[0]);
        } else {
            for (int i = 0; i < strings.length; i++) {
                if (!StringUtils.isEmpty(strings[i])) {
                    stringBuffer.append(strings[i]);
                    if (i < strings.length - 1)
                        stringBuffer.append(",");
                }
            }
        }
        return stringBuffer.toString();
    }
}
