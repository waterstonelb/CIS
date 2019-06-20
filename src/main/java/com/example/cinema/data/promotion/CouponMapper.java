package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券相关操作
 * Created by liying on 2019/4/17.
 */
@Mapper
public interface CouponMapper {

    /**
     * 插入优惠券
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);

    /**
     * 通过用户Id查询优惠券
     * @param userId
     * @return
     */
    List<Coupon> selectCouponByUser(int userId);

    /**
     * 通过优惠券Id查询优惠券
     * @param id
     * @return
     */
    Coupon selectById(int id);

    /**
     * 根据特定用户插入优惠券
     * @param couponId
     * @param userId
     */
    void insertCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    /**
     * 删除特定用户的特定优惠券
     * @param couponId
     * @param userId
     */
    void deleteCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    List<Coupon> selectCouponByUserAndAmount(@Param("userId") int userId,@Param("amount") double amount);
}
