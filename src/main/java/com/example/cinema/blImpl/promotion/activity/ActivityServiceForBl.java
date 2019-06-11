package com.example.cinema.blImpl.promotion.activity;

import java.util.*;

import com.example.cinema.po.Activity;

/**
 * Created by 梁斌 on 2019/5/27.
 */
public interface ActivityServiceForBl {
    /**
     * 根据movie查找相对应所有的activity
     * @param movieId
     * @return activities
     */
    List<Activity> getActivitiesByMovie(int movieId);
}
