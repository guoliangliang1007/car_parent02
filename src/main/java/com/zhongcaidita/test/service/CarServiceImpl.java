package com.zhongcaidita.test.service;
/*@authour guoliangliang
 *
 *@date 2018/5/4 17:08
 */

import com.zhongcaidita.test.dao.CarDao;
import com.zhongcaidita.test.interfaces.CarService;
import com.zhongcaidita.test.pojo.Car;
import com.zhongcaidita.test.utils.StaticVia;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
    long timerChage = 43200000;// 12小时
    @Autowired
    private CarDao carDao;

    //读取excel 保存数据
    public void save(String file_name) {
        List<Car> list = new ArrayList<>();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file_name));
            // 获取sheet的个数
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println(file_name +"sheet的数目:" + numberOfSheets);
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheetAt = workbook.getSheetAt(i);
                // 取得有效的行数
                int rowNumCount = sheetAt.getLastRowNum();
                if(rowNumCount==1){
                    System.out.println("数据表格为空："+file_name);
                    continue;
                }
                for (Row row : sheetAt) {
                    // 获取有效单元格个数
                     //short lastCellNum = row.getLastCellNum();

                    // System.out.println("有效的单元格个数："+lastCellNum);
                    int rowNum = row.getRowNum();
                    if (rowNum == 0) {
                        continue;
                    }
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    // System.out.println(i);
                    String plateNumber = row.getCell(0).getStringCellValue();
                    Double latitude = Double.valueOf(row.getCell(1).getStringCellValue());
                    Double longitude = Double.valueOf(row.getCell(2).getStringCellValue());
                    String format = row.getCell(3).getStringCellValue();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(format);
                    long time = date.getTime();
                    date.setTime(time);
                    String dataTimeGps = sdf.format(date);

                    Double speedGps = Double.valueOf(row.getCell(4).getStringCellValue());
                    Double mileage = Double.valueOf(row.getCell(5).getStringCellValue());
                    Double altitude = Double.valueOf(row.getCell(6).getStringCellValue());
                    Double northAngularSeparation = Double.valueOf(row.getCell(7).getStringCellValue());
                    Car car = new Car();
                    // jedis.incr("myCarId");
                    /*
                     * String str = jedis.get("myCarId");
                     * car.setCarId(Integer.parseInt(str));
                     */
                    car.setPlateNumber(plateNumber);
                    car.setLatitude(latitude);
                    car.setLongitude(longitude);
                    car.setDataTimeGps(dataTimeGps);
                    car.setSpeedGps(speedGps);
                    car.setMileage(mileage);
                    car.setAltitude(altitude);
                    car.setNorthAngularSeparation(northAngularSeparation);
                    // carDao.saveCar(car);
                    list.add(car);
                    int size = list.size();
                    if (size == 20000) {
                        carDao.save(list);
                      //  System.out.println("1");
                        list.clear();// 清空集合
                    }
                }
            }
            if(list.size()!=0){
                carDao.save(list);
                //System.out.println("2");
                list.clear();// 清空集合
            }
        } catch (Exception e) {
            //System.out.println("数据写入异常");
            throw new RuntimeException(e);
        }
    }

    //查询brand_car 数据库里的所有车的名字
    public String[] getPlateNumber() {
        return carDao.getPlateNumber();
    }

    //查询该车全部数据
    public List<Car> AllDayDate(Car car) {
        return carDao.AllDayDate(car);
    }

    //时间的更改
    public void changeTimer() throws Exception {
        Car car = new Car();
        // 查询出所有的车牌
        String[] names = carDao.getPlateNumberCar();
        if (names.length != 0 && names != null) {
            int length = names.length;
            for (int a = 0; a < length; a++) {
                System.out.println("车牌：" + names[a]);
               String brand = names[a];
               // String brand = "川AR7608";
                car.setPlateNumber(brand);
                // 查询出该车的所有时间
                List<String> dataTime = carDao.selectTimeByCar(brand);
                int flag_id = 0; // id标记
                if (0 != dataTime.size()) {
                    int size = dataTime.size();
                    for (int b = 0; b < size; b++) {
                        String timer = dataTime.get(b);
                        //String timer = "2018-02-09";
                        System.out.println("时间：" + timer);
                        String start = timer + StaticVia.fromDate; // 开始时间
                        String end = timer + StaticVia.endDate; // 结束时间
                        // 根据时间戳去查选该车一天的数据
                        car.setFromData(start);
                        car.setFromEnd(end);
                        car.setFlagTime(timer + " 12%");
                        // 先判断该车有没有12点的数据；
                        List<Car> include12 = carDao.selectOnlyInclude12(car);
                        // 查询出一天的数据
                        List<Car> oneDayDate = carDao.OneDayDateCar(car);
                        if (0 != include12.size()) {// 有1个或2个12点
                            // 查询到12点数据
                            // 查询时间,去除12点的数据
                            String fromData = timer + "%";
                            String fromEnd = timer + " 12%";
                            String plateNumber = car.getPlateNumber();
                            List<Car> onlyDateNotInclude12 = carDao.onlyDateNotInclude12(plateNumber, fromData,
                                    fromEnd);
                            int size_lang = onlyDateNotInclude12.size()-1;
                            for (int c = 0; c < size_lang; c++) {
                                Integer carId = onlyDateNotInclude12.get(c).getCarId();
                                Integer carId2 = onlyDateNotInclude12.get(c + 1).getCarId();
                                // 取相邻的两条数据id进行比较
                                int id_1 = carId.intValue() + 1;
                                int id_2 = carId2.intValue();
                                if (id_1 < id_2) {
                                    flag_id = id_2; // 记录下时间变化的分水岭
                                    continue;
                                    // break;
                                }
                            }
                            if (flag_id == 0) {
                                // 遍历oneDayDate 集合
                                // 将这一天的数据都加12小时，但是改天的第一条数据不能是12点
                                if (oneDayDate.size() != 0) {
                                    // 取这一天不包含12点的第一条数据
                                    if (onlyDateNotInclude12.size() != 0) {
                                        int id_3 = onlyDateNotInclude12.get(0).getCarId().intValue();
                                        int size1 = onlyDateNotInclude12.size() - 1;
                                        int id_5 = onlyDateNotInclude12.get(size1).getCarId().intValue();
                                        // 取这一天所有数据的第一条数据 做比较
                                        int id_4 = oneDayDate.get(0).getCarId().intValue();
                                        if (id_3 == id_4) { // 相同
                                            // 说明这一天的开始不是一12点的某一时刻开始的
                                            for (int c = 0, len3 = oneDayDate.size() - 1; c < len3; c++) {
                                                // 数据时间加12小时
                                                String timeGps = oneDayDate.get(c).getDataTimeGps();
                                                Date date = sdf2.parse(timeGps);
                                                long time = date.getTime();

                                                String timeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                                Date parse = sdf2.parse(timeGps2);
                                                long time2 = parse.getTime();
                                                if (time < time2) {
                                                    continue;
                                                }
                                                if (time2 > time) {
                                                    // 取得id
                                                    Integer carId = oneDayDate.get(c + 1).getCarId();
                                                    Integer carId2 = oneDayDate.get(size1).getCarId();
                                                    if (carId <= carId2) {
                                                        time += timerChage; // 时间加12小时
                                                        date.setTime(time);
                                                        String dataTimeGps = sdf2.format(date);
                                                        oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                                    }
                                                }
                                            }
                                        } else if (id_3 > id_4) { // 大于说明这一天的开始有12点的数据的
                                            for (int c = 0, len3 = oneDayDate.size() - 1; c < len3; c++) {
                                                int intValue = oneDayDate.get(c).getCarId().intValue();

                                                String timeGps = oneDayDate.get(c).getDataTimeGps();
                                                String timeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                                Date date = sdf2.parse(timeGps);
                                                long time = date.getTime();

                                                Date parse = sdf2.parse(timeGps2);
                                                long time2 = parse.getTime();
                                                if (intValue < id_3) {
                                                    Calendar calendar = Calendar.getInstance();
                                                    calendar.setTime(date);
                                                    int i = calendar.get(Calendar.HOUR_OF_DAY);
                                                    if (i == 12) {
                                                        calendar.set(Calendar.HOUR_OF_DAY, 00);
                                                        Date time3 = calendar.getTime();
                                                        String dataTimeGps = sdf2.format(time3);
                                                        oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                                    } else {
                                                        continue;
                                                    }

                                                } else if (id_5 < intValue) {
                                                    // 数据时间加12小时
                                                    String timeGps1 = oneDayDate.get(c).getDataTimeGps();
                                                    Calendar calendar = Calendar.getInstance();
                                                    Date date1 = sdf2.parse(timeGps1);
                                                    calendar.setTime(date1);
                                                    int i = calendar.get(Calendar.HOUR_OF_DAY);
                                                    if (i == 12) {
                                                        continue;
                                                    } else {
                                                        int size11 = oneDayDate.size();
                                                        size11 -= 2;
                                                        time += timerChage; // 时间加12小时
                                                        date1.setTime(time);
                                                        String dataTimeGps = sdf2.format(date1);
                                                        oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                                        if (c == size11) {
                                                            String dataTimeGps2 = oneDayDate.get(c + 1)
                                                                    .getDataTimeGps();
                                                            Date parse2 = sdf2.parse(dataTimeGps2);
                                                            long time3 = parse2.getTime();
                                                            time3 += timerChage;
                                                            parse2.setTime(time3);
                                                            String format = sdf2.format(parse2);
                                                            oneDayDate.get(c + 1).setDataTimeGps(format); // 重新赋值
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                        // 保存数据
                                        carDao.save_brand(oneDayDate);
                                        flag_id = 0;
                                    } else if (onlyDateNotInclude12.size() == 0) {
                                        carDao.save_brand(oneDayDate);
                                    }
                                }

                            } else if (flag_id != 0) {
                                if (oneDayDate.size() != 0) {
                                    int carId = oneDayDate.get(0).getCarId().intValue();
                                    int id_3 = onlyDateNotInclude12.get(0).getCarId().intValue();
                                    int size1 = onlyDateNotInclude12.size() - 1;
                                    int id_5 = onlyDateNotInclude12.get(size1).getCarId().intValue();
                                    if (carId < flag_id) {
                                        for (int c = 0, len3 = oneDayDate.size() - 1; c < len3; c++) {
                                            // 数据时间加12小时
                                            String timeGps = oneDayDate.get(c).getDataTimeGps();
                                            String timeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                            Date date = sdf2.parse(timeGps);
                                            long time = date.getTime();

                                            Date parse = sdf2.parse(timeGps2);
                                            long time2 = parse.getTime();
                                            if (oneDayDate.get(c).getCarId().intValue() < flag_id) {
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(date);
                                                int i = calendar.get(Calendar.HOUR_OF_DAY);
                                                if (id_3 > oneDayDate.get(c).getCarId().intValue()) {
                                                    if (i == 12) {
                                                        calendar.set(Calendar.HOUR_OF_DAY, 00);
                                                        Date time3 = calendar.getTime();
                                                        String dataTimeGps = sdf2.format(time3);
                                                        oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                                    }
                                                }
                                                if (id_5 < oneDayDate.get(c).getCarId().intValue()) {
                                                    Calendar calendar2 = Calendar.getInstance();
                                                    calendar2.setTime(date);
                                                    int i2 = calendar2.get(Calendar.HOUR_OF_DAY);
                                                    if (i2 == 12) {
                                                        continue;
                                                    }
                                                }
                                            } else if (oneDayDate.get(c).getCarId().intValue() >= flag_id) {
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(date);
                                                int i = calendar.get(Calendar.HOUR_OF_DAY);
                                                if (i == 12) {
                                                    continue;
                                                } else {
                                                    int size11 = oneDayDate.size();
                                                    size11 -= 2;
                                                    // long time =
                                                    // date.getTime();
                                                    time += timerChage; // 时间加12小时
                                                    date.setTime(time);
                                                    String dataTimeGps = sdf2.format(date);
                                                    oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                                    if (c == size11) {
                                                        String dataTimeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                                        Date parse2 = sdf2.parse(dataTimeGps2);
                                                        long time3 = parse2.getTime();
                                                        time3 += timerChage;
                                                        parse2.setTime(time3);
                                                        String format = sdf2.format(parse2);
                                                        oneDayDate.get(c + 1).setDataTimeGps(format); // 重新赋值
                                                    }
                                                }
                                            }

                                        }
                                    }

                                    // 保存数据
                                    carDao.save_brand(oneDayDate);
                                    flag_id = 0;
                                }
                            }
                        } else if (include12.size() == 0) {
                            // 没有12点的数据
                            for (int c = 0, len3 = oneDayDate.size() - 1; c < len3; c++) {
                                // 数据时间加12小时
                                String timeGps = oneDayDate.get(c).getDataTimeGps();
                                Date date = sdf2.parse(timeGps);
                                long time = date.getTime();

                                String timeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                Date parse = sdf2.parse(timeGps2);
                                long time2 = parse.getTime();
                                boolean flag = time < time2;

                                time += timerChage; // 时间加12小时
                                date.setTime(time);
                                String dataTimeGps = sdf2.format(date);
                                oneDayDate.get(c).setDataTimeGps(dataTimeGps); // 重新赋值
                                int size1 = oneDayDate.size();
                                size1 -= 2;
                                if (c == size1) {
                                    String dataTimeGps2 = oneDayDate.get(c + 1).getDataTimeGps();
                                    Date parse2 = sdf2.parse(dataTimeGps2);
                                    long time3 = parse2.getTime();
                                    time3 += timerChage;
                                    parse2.setTime(time3);
                                    String format = sdf2.format(parse2);
                                    oneDayDate.get(c + 1).setDataTimeGps(format); // 重新赋值
                                }

                            }

                            // 保存数据
                            carDao.save_brand(oneDayDate);
                        }
                    }
                }
            }
        }


    }

}
