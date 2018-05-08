package com.zhongcaidita.test.dao;
/*@authour guoliangliang
 *
 *@date 2018/5/4 17:19
 */

import com.zhongcaidita.test.pojo.Car;

import java.util.List;

public interface CarDao {

    void save(List<Car> list);

    String[] getPlateNumber();

    List<Car> AllDayDate(Car car);

    //更改时间
    String[] getPlateNumberCar();

    List<String> selectTimeByCar(String brand);

    List<Car> selectOnlyInclude12(Car car);

    List<Car> OneDayDateCar(Car car);

    List<Car> onlyDateNotInclude12(String plateNumber, String fromData, String fromEnd);

    void save_brand(List<Car> oneDayDate);   //更改时间结束
}
