package me.jinkun.rds.common.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 转换基类 <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class BaseConvert {
    public final static String P_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String P_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * Description: 默认Date转String，格式：yyyy-MM-dd HH:mm:ss <br/>
     */
    public static String dateToString(Date date) {
        return dateToString(date, BaseConvert.P_YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateToString(Date date, String p) {
        if(date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        return sdf.format(date);
    }

    /**
     * Description: 默认String转Date，格式：yyyy-MM-dd HH:mm:ss <br/>
     */
    public static Date stringToDate(String str) {
        return stringToDate(str, BaseConvert.P_YYYY_MM_DD_HH_MM_SS);
    }

    public static Date stringToDate(String str, String p) {
        if(str == null || "".equals(str)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}