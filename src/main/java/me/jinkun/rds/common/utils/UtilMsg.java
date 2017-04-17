package me.jinkun.rds.common.utils;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/3/24.
 */
public class UtilMsg {
    public static void sendMsg(String phone, String content) {
        System.out.println("发送短信：To [" + phone + "]，" + "Content [" + content + "]");
    }
}
