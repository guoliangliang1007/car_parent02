package com.zhongcaidita.test.controller.readExcel;
/*@authour guoliangliang
 *
 *@date 2018/5/4 16:58
 */

import com.zhongcaidita.test.interfaces.CarService;
import com.zhongcaidita.test.utils.XlsFileUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;

@Controller
public class ReadExcelController {


        @Autowired
        private CarService carService;

        @RequestMapping(value="readExcel.action")
        public void  readExcel(){
            String path= "C:\\Users\\User\\Desktop\\test";
            File file = new File(path);
            boolean exists = file.exists();
            if(!exists){
                System.out.println("文件路徑不對");
            }else{
                //获取文件夹下.xls结尾的文件
                ArrayList<Object> xlsFileUtile = XlsFileUtile.getXlsFileUtile(path);
                int size = xlsFileUtile.size();
                for(int a = 0;a<size;a++){
                    String file_Name = xlsFileUtile.get(a).toString();
                    carService.save(file_Name);
                }
            }
        }
}
