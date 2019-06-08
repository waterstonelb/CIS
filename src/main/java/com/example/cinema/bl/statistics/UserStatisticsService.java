package com.example.cinema.bl.statistics;

import java.util.Date;

import com.example.cinema.vo.ResponseVO;

public interface UserStatisticsService {
    /**
     * 获取购票记录
     * @param date
     * @return
     */
    ResponseVO getUserBuyRecord();

    /**
     * 获取充值记录
     * @return
     */
    ResponseVO getUserChargeRecord();
}
