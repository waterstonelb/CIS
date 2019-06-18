 package com.example.cinema.controller.statistics;
import com.example.cinema.bl.statistics.UserStatisticsService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yzh
 * @date 2019/6/18 1:34 PM
 * 实现用户的相关统计功能（记录查询）
 */
@RestController
public class UserStatistisController {
    @Autowired
    private UserStatisticsService UserstatisticsService;

    /*
     * @param id
     * @return ResponseVO
     * 查询用户历史消费记录以vo形式返回
     */
    @RequestMapping(value = "userstatistics/BuyRecord", method = RequestMethod.GET)
    public ResponseVO getUserBuyRecord(@RequestParam int id){
        return UserstatisticsService.getUserBuyRecord(id);
    }
    /*
     * @param id
     * @return ResponseVO
     * 查询用户历史充值记录以vo形式返回
     */
    @RequestMapping(value = "userstatistics/ChargeRecord", method = RequestMethod.GET)
    public ResponseVO getUserChargeRecord(@RequestParam int id){
        return UserstatisticsService.getUserChargeRecord(id);
    }
    
   }