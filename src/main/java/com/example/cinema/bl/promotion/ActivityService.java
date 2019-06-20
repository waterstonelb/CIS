package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;

/**
 * Created by liying on 2019/4/20.
 */
public interface ActivityService {
    
    /**
     * 发布活动
     * @param activityForm
     * @return
     */
    ResponseVO publishActivity(ActivityForm activityForm);

    /**
     * 获取活动
     * @return
     */
    ResponseVO getActivities();

    /**
     * 通过电影Id筛选活动
     * @param movieId
     * @return
     */
    ResponseVO selectActivitiesByMovie(int movieId);

    /**
     * 筛选面向所有电影的活动
     * @return
     */
    ResponseVO selectActivitiesWithoutMovie();


}
