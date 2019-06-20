package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.vipCoupon;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    /**
     * 新增会员卡
     * @param userId
     * @param cardId
     * @return
     */
    ResponseVO addVIPCard(int userId,int cardId);

    /**
     * 通过会员卡号获取会员卡    userId ，cardId（等级），id（会员卡号）
     * @param id
     * @return
     */
    ResponseVO getCardById(int id);

    /**
     * 获取VIP卡相关信息（金、银、普通卡）
     * @return
     */
    ResponseVO getVIPInfo();

    /**
     * 会员卡充值
     * @param vipCardForm
     * @return
     */
    ResponseVO charge(VIPCardForm vipCardForm);

    /**
     * 通过用户Id获取会员卡
     * @param userId
     * @return
     */
    ResponseVO getCardByUserId(int userId);

    /**
     * 赠送优惠券
     * @param vipCoupon
     * @return
     */
    ResponseVO sendCoupon(vipCoupon vipCoupon);

    /**
     * 升级会员卡
     * @param vipCardForm
     * @return
     */
    ResponseVO upgradeCard(VIPCardForm vipCardForm);
}
