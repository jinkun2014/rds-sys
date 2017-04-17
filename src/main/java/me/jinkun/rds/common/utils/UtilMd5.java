package me.jinkun.rds.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/1/12.
 */
public class UtilMd5 {

    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt, 1).toString();
    }

    public static String md5(String str, String salt, int hashIterations) {
        return new Md5Hash(str, salt, hashIterations).toString();
    }

}
