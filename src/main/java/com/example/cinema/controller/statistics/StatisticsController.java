 package com.example.cinema.controller.statistics;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.bl.statistics.UserStatisticsService;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yzh
 * @date 2019/6/18 23:42 PM
 * 实现影院的相关查询功能
 */
@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;e;
    /**
     * 获取某日各影片排片率统计数据
     * @param date
     * @return
     */
    @RequestMapping(value = "statistics/scheduleRate", method = RequestMethod.GET)
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date){
        return statisticsService.getScheduleRateByDate(date);
    }
    /**
     * 获取所有电影的累计票房(降序排序，且包含已下架的电影)
     * @return
     */
    @RequestMapping(value = "statistics/boxOffice/total", method = RequestMethod.GET)
    public ResponseVO getTotalBoxOffice(){
        return statisticsService.getTotalBoxOffice();
    }
    /**
     * 客单价：（某天的客单价=当天观众购票所花金额/购票人次数）
     * 返回值为过去7天内每天客单价
     * @return
     */
    @RequestMapping(value = "statistics/audience/price", method = RequestMethod.GET)
    public ResponseVO getAudiencePrice(){
        return statisticsService.getAudiencePriceSevenDays();
    }
    /**
     * 获取所有电影某天的上座率
     * @param date
     * @return
     */
    @RequestMapping(value = "statistics/placing/rate", method = RequestMethod.GET)
    public ResponseVO getMoviePlacingRateByDate(@RequestParam(required = false) String date){
    	date=date.replace('/', '-');
    	System.out.println(date);
        return statisticsService.getMoviePlacingRateByDate(date);
    }
    /**
     * 获取最近days天内，最受欢迎的movieNum个电影(可以简单理解为最近days内票房越高的电影越受欢迎)
     * @param days
     * @param movieNum
     * @return
     */
    @RequestMapping(value = "statistics/popular/movie", method = RequestMethod.GET)
    public ResponseVO getPopularMovies(@RequestParam("days") int days,@RequestParam("movieNum") int movieNum){
        return statisticsService.getPopularMovies(days, movieNum);
    }

    /**
     * 获取用户消费排名
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "statistics/amount",method = RequestMethod.GET)
    public ResponseVO getAmountHistory(@RequestParam String startDate, @RequestParam String endDate){
        return statisticsService.getAmountHistory(startDate,endDate);
    }

}
