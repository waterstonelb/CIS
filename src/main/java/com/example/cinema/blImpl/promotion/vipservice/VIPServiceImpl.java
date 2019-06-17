package com.example.cinema.blImpl.promotion.vipservice;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoVO;
import com.example.cinema.vo.vipCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService, VIPServiceForBl {
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    CouponServiceForBl couponServiceForBl;

    /**
     * 根据userid获取cardId
     * @return -1:没有搜索到card ; 其他：cardId
     * 
     */
    @Override
    public int getCardId(int userId) {
        try {
            return vipCardMapper.selectCardByUserId(userId).getCardId();
        } catch (Exception e) {
            // e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ResponseVO addVIPCard(int userId, int cardId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        vipCard.setCardId(cardId);
        try {
            int id = vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo() {
        VIPInfoVO vipInfoVO = new VIPInfoVO();
        vipInfoVO.setDescription(VIPCard.description);
        vipInfoVO.setPrice(VIPCard.price);
        return ResponseVO.buildSuccess(vipInfoVO);
    }

    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        double balance = vipCard.calculate(vipCardForm.getAmount(), vipCardForm.getTargetAmount(), vipCardForm.getDiscountAmount());
        vipCard.setBalance(vipCard.getBalance() + balance);
        UserChargeRecord userChargeRecord=new UserChargeRecord();
        userChargeRecord.setchargeNum(vipCardForm.getAmount());
        userChargeRecord.setUserId(vipCard.getUserId());
        try {
            vipCardMapper.insertUserCharge(userChargeRecord);
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if (vipCard == null) {
                return ResponseVO.buildFailure("用户卡不存在");
            }
            vipCard.setBalance((double)Math.round(vipCard.getBalance()*100)/100);
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public int buyTicket(int userId, double totals) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            vipCardMapper.updateCardBalance(vipCard.getId(), vipCard.getBalance() - totals);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public double getBalance(int userId){
        try{
            return vipCardMapper.selectCardByUserId(userId).getBalance();
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public ResponseVO sendCoupon(vipCoupon vipCoupon){
        try{
            couponServiceForBl.insertCouponUser(vipCoupon.getCouponId(),vipCoupon.getUserId());
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("赠送失败");
        }
    }

    /**
     * 退票，成功返回0
     */
    @Override
    public int returnTicket(int userId, double totals) {
        try {
            vipCardMapper.updateCardBalance(vipCardMapper.selectCardByUserId(userId).getId(), totals);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ResponseVO upgradeCard(VIPCardForm vipCardForm){
        try{
            double balance=vipCardMapper.selectCardById(vipCardForm.getVipId()).getBalance()-vipCardForm.getAmount();
            vipCardMapper.updateCard(vipCardForm.getVipId(),balance,vipCardForm.getCardId());
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("升级失败");
        }
    }
}
