package com.example.cinema.bl.statistics;

import com.example.cinema.vo.ResponseVO;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author yzh
 * @date 2019/6/18 23:49 PM
 * 完成影院方统计相关功能
 */
public interface StatisticsService {
    /**
     * 获取某日各影片排片率统计数据
     * @param date
     * @return
     */
    ResponseVO getScheduleRateByDate(Date date);

    /**
     * 获取所有电影的累计票房(降序排序，且包含已下架的电影)
     * @return
     */
    ResponseVO getTotalBoxOffice();

    /**
     * 客单价：（某天的客单价=当天观众购票所花金额/购票人次数）
     * 返回值为过去7天内每天客单价
     * @return
     */
    ResponseVO getAudiencePriceSevenDays();


    /**
     * 上座率参考公式：假设某影城设有n 个电影厅、m 个座位数，相对上座率=观众人次÷放映场次÷m÷n×100%
     * 此方法中运用到的相应的操作数据库的接口和实现请自行定义和实现，相应的结果需要自己定义一个VO类返回给前端
     * @param date
     * @return
     */
    ResponseVO getMoviePlacingRateByDate(String date);

    /**
     * 此方法中运用到的相应的操作数据库的接口和实现请自行定义和实现，相应的结果需要自己定义一个VO类返回给前端
     * @param days
     * @param movieNum
     * @return
     */
    ResponseVO getPopularMovies(int days, int movieNum);

    /**
     * 获取用户消费排名
     * @param startData
     * @param endData
     * @return
     */
    ResponseVO getAmountHistory(String startDate,String endDate);
}
