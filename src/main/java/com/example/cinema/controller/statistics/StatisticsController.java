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
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private MovieLikeService movielikeService;

    @RequestMapping(value = "statistics/scheduleRate", method = RequestMethod.GET)
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date){
        //此处date为非必填参数，若不填则默认为当天
        return statisticsService.getScheduleRateByDate(date);
    }

    @RequestMapping(value = "statistics/boxOffice/total", method = RequestMethod.GET)
    public ResponseVO getTotalBoxOffice(){
        return statisticsService.getTotalBoxOffice();
    }

    @RequestMapping(value = "statistics/audience/price", method = RequestMethod.GET)
    public ResponseVO getAudiencePrice(){
        return statisticsService.getAudiencePriceSevenDays();
    }

    @RequestMapping(value = "statistics/placing/rate", method = RequestMethod.GET)
    public ResponseVO getMoviePlacingRateByDate(@RequestParam(required = false) String date){
        return statisticsService.getMoviePlacingRateByDate(date);
    }

    @RequestMapping(value = "statistics/popular/movie", method = RequestMethod.GET)
    public ResponseVO getPopularMovies(@RequestParam("days") int days,@RequestParam("movieNum") int movieNum){
        return statisticsService.getPopularMovies(days, movieNum);
    }

    @RequestMapping(value = "userstatistics/BuyRecord", method = RequestMethod.GET)
    public ResponseVO getUserBuyRecord(@RequestParam int id){
        return statisticsService.getUserBuyRecord(id);
    }
    
    @RequestMapping(value = "userstatistics/ChargeRecord", method = RequestMethod.GET)
    public ResponseVO getUserChargeRecord(@RequestParam int id){
        return statisticsService.getUserChargeRecord(id);
    }
    
    @RequestMapping(value = "statistics/userlike", method = RequestMethod.GET)
    public ResponseVO getLikeMovieList(){
        return movielikeService.getlikemovielist();
        }


}
