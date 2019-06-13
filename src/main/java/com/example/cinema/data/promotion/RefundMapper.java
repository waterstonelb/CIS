package com.example.cinema.data.promotion;

import com.example.cinema.po.RefundPolicy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefundMapper{
    /**
     * 
     * @param refund_day
     * @param refund_hour
     * @return
     */
    int addRefundPolicy(@Param("day") Float refund_day,@Param("hour") Float refund_hour);

    /**
     * 
     * @return
     */
    RefundPolicy getRefundPolicy();
}