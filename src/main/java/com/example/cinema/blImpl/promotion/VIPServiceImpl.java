package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.promotion.VIPServiceForBl;
import com.example.cinema.data.promotion.VIPCardMapper;
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
    CouponService couponService;

    @Override
    public int getCardId(int userId) {
        try {
            return vipCardMapper.selectCardByUserId(userId).getCardId();
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
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
    public double getBalance(int userId) catch (Exception e){
            e.printStackTrace();
            return  0;
        }
    }
    @Override
    public ResponseVO sendCoupon(vipCoupon vipCoupon){
        try{
            couponService.issueCoupon(vipCoupon.getCouponId(),vipCoupon.getUserId());
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("赠送失败");
        }
    }
}
