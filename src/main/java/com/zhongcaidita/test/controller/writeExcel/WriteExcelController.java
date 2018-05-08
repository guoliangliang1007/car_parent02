package com.zhongcaidita.test.controller.writeExcel;

import com.zhongcaidita.test.interfaces.CarService;
import com.zhongcaidita.test.pojo.Car;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/*@authour guoliangliang
 *
 *@date 2018/5/7 10:12
 */
@Controller
public class WriteExcelController {

    @Autowired
    private CarService carService;
    Car car  = new Car();

    @RequestMapping(value="writeExcel.action")
    public void writeExcel(){
        //查询出数据库中的所有车牌号码
        String[] number = carService.getPlateNumber();
        //String[] number = {"川AV7109"};
        for (String plateNumber : number) {
            //根据车牌号码查询出该车的所有时间
            car.setPlateNumber(plateNumber);
            List<Car> oneDayDate = carService.AllDayDate(car);
            String filePath = "F:\\下载\\"+plateNumber+".xlsx";
            try {
                staticExcel(filePath, plateNumber, oneDayDate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void staticExcel(String filePath,String names,List<Car> oneDayDate) throws IOException{
        File file = new File(filePath);
        boolean exists = file.exists();
        int flag =0;
        int y=0;
        if(!exists){
            file.createNewFile();
        }else{
            file.delete(); //删除 再创建
            file.createNewFile();
        }
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        SXSSFSheet sheet = workBook.createSheet("运行轨迹"+y); //sheet的名字是车牌号
        y++;
        SXSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("车牌号");
        row.createCell(1).setCellValue("纬度");
        row.createCell(2).setCellValue("经度");
        row.createCell(3).setCellValue("GPS时间");
        row.createCell(4).setCellValue("GPS速度");
        row.createCell(5).setCellValue("里程");
        row.createCell(6).setCellValue("海拔");
        row.createCell(7).setCellValue("正北方向夹角");
        int size = oneDayDate.size();
        for(int a = 0;a<size;a++){
            if(flag>65320){

                sheet =workBook.createSheet("运行轨迹"+y); //sheet的名字是车牌号
                y++;
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("车牌号");
                row.createCell(1).setCellValue("纬度");
                row.createCell(2).setCellValue("经度");
                row.createCell(3).setCellValue("GPS时间");
                row.createCell(4).setCellValue("GPS速度");
                row.createCell(5).setCellValue("里程");
                row.createCell(6).setCellValue("海拔");
                row.createCell(7).setCellValue("正北方向夹角");
                flag =0;

            }
            flag++;
            row = sheet.createRow(flag);
            Car car= oneDayDate.get(a);
            row.createCell(0).setCellValue(car.getPlateNumber());
            row.createCell(1).setCellValue(car.getLatitude());
            row.createCell(2).setCellValue(car.getLongitude());
            row.createCell(3).setCellValue(car.getDataTimeGps());
            row.createCell(4).setCellValue(car.getSpeedGps());
            row.createCell(5).setCellValue(car.getMileage());
            row.createCell(6).setCellValue(car.getAltitude());
            row.createCell(7).setCellValue(car.getNorthAngularSeparation());

        }
        workBook.write(new FileOutputStream(new File(filePath)));
        workBook.close();
    }
}
