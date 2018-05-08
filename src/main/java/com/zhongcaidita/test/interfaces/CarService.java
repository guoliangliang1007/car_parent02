package com.zhongcaidita.test.interfaces;
/*@authour guoliangliang
 *
 *@date 2018/5/4 17:06
 */

import com.zhongcaidita.test.pojo.Car;

import java.util.List;

public interface CarService {


    void save(String file_name);

    String[] getPlateNumber();

    List<Car> AllDayDate(Car car);

    void changeTimer() throws Exception;
}
