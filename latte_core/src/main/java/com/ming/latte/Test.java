package com.ming.latte;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

    /**
     * 格式化的模板
     */
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";


    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getTimeFormatName("DOWN_LOAD"));
    }
}
