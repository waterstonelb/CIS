package com.example.cinema.data.promotion;

import com.example.cinema.po.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动相关操作
 * Created by liying on 2019/4/20.
 */
@Mapper
public interface ActivityMapper {

    /**
     * 新增活动
     * @param activity
     * @return
     */
    int insertActivity(Activity activity);

    /**
     * 新增针对特定电影的活动
     * @param activityId
     * @param movieId
     * @return
     */
    int insertActivityAndMovie(@Param("activityId") int activityId,@Param("movieId") List<Integer> movieId);

    /**
     * 查询活动
     * @return
     */
    List<Activity> selectActivities();

    /**
     * 根据特定电影查询活动
     * @param movieId
     * @return
     */
    List<Activity> selectActivitiesByMovie(int movieId);

    /**
     * 通过活动Id查询活动
     * @param id
     * @return
     */
    Activity selectById(int id);

    /**
     * 筛选出适用于所有电影的活动
     * @return
     */
    List<Activity> selectActivitiesWithoutMovie();






}
