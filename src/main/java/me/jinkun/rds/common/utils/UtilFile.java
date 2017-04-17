package me.jinkun.rds.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/3/23.
 */
public class UtilFile {
    public static File createDir(String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File createFile(String path, String fileName) throws IOException {
        return new File(path, fileName);
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        return IOUtils.copy(input, output);
    }
}
