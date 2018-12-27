package com.yanchun.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {

    private static String REGEX = "1[3|4|5|7|8][0-9]\\d{8}";
    private static Pattern P = Pattern.compile(REGEX);

    /**
     * 校验手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return Boolean.FALSE;
        }

        Matcher m = P.matcher(phone);
        return m.matches();
    }
}
