package com.example.cinema.controller.statistics;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.vo.ResponseVO;

public class MovieLikeController {
    @Autowired
    public MovieLikeService movielikeservice;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseVO getLikeMovieList(){
        return movielikeservice.getlikemovielist();
    }
}
