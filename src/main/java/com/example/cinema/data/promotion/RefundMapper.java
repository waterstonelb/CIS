package com.example.cinema.data.promotion;

import com.example.cinema.po.RefundPolicy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 退票策略
 */
@Mapper
public interface RefundMapper{
    /**
     * 增添退票策略
     * @param refund_day
     * @param refund_hour
     * @return
     */
    int addRefundPolicy(@Param("day") Float refund_day,@Param("hour") Float refund_hour);

    /**
     * 得到最新的退票策略
     * @return
     */
    RefundPolicy getRefundPolicy();
}