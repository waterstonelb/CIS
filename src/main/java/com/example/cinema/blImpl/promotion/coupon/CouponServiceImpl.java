package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(couponMapper.selectCouponByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    /*为其他bl提供的桩
    public List<Coupon> getCouponByUserId(int userId){
        List<Coupon> coupons=new ArrayList<>();
        Coupon coupon=new Coupon();
        coupon.setName("test");
        coupon.setId(1);
        coupons.add(coupon);
        return coupons;
    }*/
    @Override
    public List<Coupon> getCouponByUserId(int userId){
        try {
            return couponMapper.selectCouponByUser(userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Coupon addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            coupon.setLevel(couponForm.getLevel());
            couponMapper.insertCoupon(coupon);
            return coupon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public void insertCouponUser(int couponId,int userId){
        try {
            couponMapper.insertCouponUser(couponId,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteUserCoupon(int userId,int couponId){
        try{
            couponMapper.deleteCouponUser(couponId,userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /*为其他bl提供的桩
    public Coupon getCoupon(int couponId){
        Coupon coupon=new Coupon();
        coupon.setId(1);
        coupon.setName("test");
        return coupon;
    }
     */
    @Override
    public Coupon getCoupon(int couponId){
        try{
           return couponMapper.selectById(couponId);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @Override
    public List<Coupon> getTicketCoupons(int userId,double amount){
        try{
            return couponMapper.selectCouponByUserAndAmount(userId,amount);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
