package com.example.cinema.data.statistics;

import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.po.PopularMovies;
import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.po.PlacingRate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface UserStatisticsMapper {
	/**
     * 查询购买记录
     * @param date
     * @return
     */
    List<UserBuyRecord> SelectBuyRecord();
    /**
     * 查询充值记录
     * @param 
     * @return
     */
    List<UserChargeRecord> SelectChargeRecord();
}