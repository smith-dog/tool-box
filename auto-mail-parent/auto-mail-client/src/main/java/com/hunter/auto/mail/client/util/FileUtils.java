package com.hunter.auto.mail.client.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: smith-dog
 * @Date: 2018/7/6 13:58
 * @Description:
 */
public class FileUtils {


    /**
     * 获取路径下所有的文件
     * @param path
     * @return
     */
    public static List<File> getPathResource(String path) {
        List<File> fileList = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile(path);
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return fileList;
    }
}
