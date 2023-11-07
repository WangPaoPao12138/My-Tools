package com.wjx.clazzload;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 类加载工具类
 *
 * @author Gin
 * @description
 * @date 2023/11/6 17:31
 */
public class FileUtil {
    public static void main(String[] args) {
//        File[] files = loadFiles("D:\\Df\\git\\sivf-backend\\sivf-web\\src\\main\\java");
//        System.out.println(files);
        ArrayList<File> result = new ArrayList<>();
        loadFilesTree(result, "D:\\Df\\git\\sivf-backend\\sivf-web\\src\\main\\java");
        System.out.println("result : " + result);
    }

    //文件加载
    public static File loadFile(String filePath) {
        if (filePath == null || "".equals(filePath)) return null;
        File directory = new File(filePath);
        return directory;
    }

    public static File[] loadFiles(String filePath) {
        File directory = loadFile(filePath);
        File[] files = directory.listFiles();
        return files;
    }

    public static void loadFilesTree(ArrayList<File> result, String filePath) {
        result.add(loadFile(filePath));
        File[] files = loadFiles(filePath);
        if (files != null) {
            for (File file : files) {
                loadFilesTree(result, file.getAbsolutePath());
            }
        }
    }
}
