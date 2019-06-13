package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ResponseVO;

public interface RefundService{
    /**
     * 
     * @param refund_day
     * @param refund_hour
     * @return
     */
    ResponseVO update(Float refund_day,Float refund_hour);

    /**
     * 
     * @return
     */
    ResponseVO getRefundPolicy();
}
