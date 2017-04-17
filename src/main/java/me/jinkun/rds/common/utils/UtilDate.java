package me.jinkun.rds.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/3/23.
 */
public class UtilDate {
    public final static String yyyyMMdd = "yyyyMMdd";

    public static String formDate(Date date, String parttern) {
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        return sdf.format(date);
    }
}
