package com.example.cinema.controller.statistics;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * @author yzh
 * @date 2019/6/18 23:33 PM
 * 实现对喜爱人数的统计的控制
 */
@RestController
public class MovieLikeController {
    @Autowired
    public MovieLikeService movielikeservice;
    /*
     * 获得电影的喜爱人数列表（以降序排列）
     */
    @RequestMapping(value = "movielikestatistics/userlike", method = RequestMethod.GET)
    public ResponseVO getLikeMovieLists(){
        return movielikeservice.getlikemovielist();
        }
    
}
