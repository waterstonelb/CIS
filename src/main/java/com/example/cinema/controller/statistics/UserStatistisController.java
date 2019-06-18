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
public class UserStatistisController {
    @Autowired
    private UserStatisticsService UserstatisticsService;

    
    @RequestMapping(value = "userstatistics/BuyRecord", method = RequestMethod.GET)
    public ResponseVO getUserBuyRecord(@RequestParam int id){
        return UserstatisticsService.getUserBuyRecord(id);
    }
    
    @RequestMapping(value = "userstatistics/ChargeRecord", method = RequestMethod.GET)
    public ResponseVO getUserChargeRecord(@RequestParam int id){
        return UserstatisticsService.getUserChargeRecord(id);
    }
    
   }