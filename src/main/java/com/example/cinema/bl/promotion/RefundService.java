package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ResponseVO;

public interface RefundService{
    /**
     * 更新退票策略
     * @param refund_day
     * @param refund_hour
     * @return
     */
    ResponseVO update(Float refund_day,Float refund_hour);

    /**
     * 得到退票策略
     * @return
     */
    ResponseVO getRefundPolicy();
}
