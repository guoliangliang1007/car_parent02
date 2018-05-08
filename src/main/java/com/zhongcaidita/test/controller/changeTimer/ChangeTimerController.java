package com.zhongcaidita.test.controller.changeTimer;

/*@authour guoliangliang
 *
 *@date 2018/5/7 10:30
 */

import com.zhongcaidita.test.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChangeTimerController {
    @Autowired
    private CarService carService;

    @RequestMapping(value="changeTimer.action")
    public void changeTimer() throws Exception{
        carService.changeTimer();
    }
}
