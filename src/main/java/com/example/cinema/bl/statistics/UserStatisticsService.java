package com.example.cinema.bl.statistics;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.example.cinema.vo.ResponseVO;
/*
 * @author yzh
 * @date 2019/6/18 23:28 PM
 * 完成用户相关统计功能
 */
public interface UserStatisticsService{
    /**
     * 获取用户购票记录
     * @param id
     * @return ResponseVO
     */
    ResponseVO getUserBuyRecord(int id);

    /**
     * 获取用户充值记录
     * @param id
     * @return ResponseVO
     */
    ResponseVO getUserChargeRecord(int id);
}
