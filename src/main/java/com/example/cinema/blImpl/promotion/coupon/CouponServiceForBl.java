package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;

import java.util.List;

public interface CouponServiceForBl {
    /**
     *
     * @param userId
     * @param couponId
     * @return  是否删除成功
     */
    boolean deleteUserCoupon(int userId,int couponId);

    /**
     *
     * @param couponId
     * @return 对应Id的优惠券
     */
    Coupon getCoupon(int couponId);

    /**
     * 获取优惠券
     * @param userId
     * @param amount
     * @return
     */
    List<Coupon> getTicketCoupons(int userId,double amount);

    /**
     * 获取用户的优惠券
     * @param UserId
     * @return
     */
    List<Coupon> getCouponByUserId(int UserId);

    /**
     * 插入优惠券
     * @param couponId
     * @param userId
     */
    void insertCouponUser(int couponId, int userId);

    /**
     * 新增优惠券
     * @param couponForm
     * @return
     */
    Coupon addCoupon(CouponForm couponForm);
}
