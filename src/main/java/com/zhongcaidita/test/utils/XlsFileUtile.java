package com.zhongcaidita.test.utils;
/*@authour guoliangliang
 *
 *@date 2018/5/4 13:45
 * 获取文件夹下的所有xls文件
 */

import java.io.File;
import java.util.ArrayList;

public class XlsFileUtile {
    public static ArrayList<Object> getXlsFileUtile(String FilePath) {
        File file = new File(FilePath);
        File[] listFiles = file.listFiles();
        ArrayList<Object> listFile = new ArrayList<>();
        int listFilesSize = listFiles.length;
        for (int i = 0; i < listFilesSize; i++) {
            File fileName = listFiles[i];
            String name = fileName.getName();
            String suffix = name.substring(name.lastIndexOf(".") + 1); //获取文件的后缀
            if ("xls".equals(suffix)) {
                listFile.add(fileName);
            }
        }
        return listFile;
    }
}
