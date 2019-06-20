package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ResponseVO;

/**
 * Created by liying on 2019/4/17.
 */
public interface CouponService {

    /**
     * 通过用户Id筛选优惠券
     * @param userId
     * @return
     */
    ResponseVO getCouponsByUser(int userId);

    /**
     * 发布优惠券
     * @param couponId
     * @param userId
     * @return
     */
    ResponseVO issueCoupon(int couponId,int userId);

}
