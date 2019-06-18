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
@RestController
public class MovieLikeController {
    @Autowired
    public MovieLikeService movielikeservice;

    @RequestMapping(value = "statistics/mostFavourite", method = RequestMethod.GET)
    public ResponseVO getLikeMovieList(){
        return movielikeservice.getlikemovielist();
    }
    @RequestMapping(value = "movielikestatistics/userlike", method = RequestMethod.GET)
    public ResponseVO getLikeMovieLists(){
        return movielikeservice.getlikemovielist();
        }
    
}
