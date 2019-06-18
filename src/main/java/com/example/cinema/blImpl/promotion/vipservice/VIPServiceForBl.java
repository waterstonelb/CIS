package com.example.cinema.blImpl.promotion.vipservice;

public interface VIPServiceForBl {
    /**
     * 根据用户Id查找cardId
     * @param userId
     * @return
     */
    int getCardId(int userId);

    /**
     * 会员卡扣费
     * @param userId
     * @param totals
     * @return
     */
    int buyTicket(int userId,double totals);

    /**
     * 将退票金额返还会员卡
     * @param userId
     * @param totals
     * @return
     */
    int returnTicket(int userId,double totals);

    /**
     * 根据用户id获取会员卡的金额
     * @param useId
     * @return
     */
    double getBalance(int useId);
}
