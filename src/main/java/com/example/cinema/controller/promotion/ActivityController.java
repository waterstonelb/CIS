package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by liying on 2019/4/20.
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * 发布活动
     * @param activityForm
     * @return
     */
    @PostMapping("/publish")
    public ResponseVO publishActivity(@RequestBody ActivityForm activityForm){
        return activityService.publishActivity(activityForm);
    }
    /**
     * 查询活动
     * @return
     */
    @GetMapping("/get")
    public ResponseVO getActivities(){
        return activityService.getActivities();
    }


}
