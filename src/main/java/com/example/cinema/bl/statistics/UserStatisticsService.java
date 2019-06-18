package com.example.cinema.bl.statistics;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.example.cinema.vo.ResponseVO;
public interface UserStatisticsService{
    /**
     * 获取购票记录
     * @param date
     * @return
     */
    ResponseVO getUserBuyRecord(int id);

    /**
     * 获取充值记录
     * @return
     */
    ResponseVO getUserChargeRecord(int id);
}
