package com.example.service_hi.system_package.util.url;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ApplicationURLUtils {

    //获取项目下的resources路径
    private static final  String  CLASS_PATH = System.getProperty("user.dir")+"/service_hi/src/main/resources/static/staticDir/";

    public static String getApplicationRelative() {
        return CLASS_PATH;
    }

    public static String getTargetURL(String fileNam){
        String TARGET_PATH = null;
        try {
            TARGET_PATH = ResourceUtils.getURL("classpath:").getPath() + fileNam;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(TARGET_PATH);
        if (!file.exists()) { //用来测试此路径名表示的文件或目录是否存在
            if (file.isDirectory()){
                File file1 = new File(TARGET_PATH);
                if(!file1 .exists()) {
                    file1.mkdirs();//创建目录
                }
            }else {
                File file2 = new File(TARGET_PATH);
                if(!file2 .exists()) {
                    try {
                        file2.createNewFile();//创建文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return TARGET_PATH;
    }

    public  static String getCreateFileURL(String string){
        String fileName = CLASS_PATH + string;
        File file = new File(fileName);
        /**
         * E:\\SpringCloudApplication\\application_eureka_service\\service_hi\\src\\main\\resources\\static\\staticDir
         */
        if (!file.exists()) { //用来测试此路径名表示的文件或目录是否存在
            if (file.isDirectory()){
                File file1 = new File(fileName);
                if(!file1 .exists()) {
                    file1.mkdirs();//创建目录
                }
            }else {
                File file2 = new File(fileName);
                if(!file2 .exists()) {
                    try {
                        file2.createNewFile();//创建文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileName;
    }
}
