package com.example.cinema.bl.promotion;

public interface VIPServiceForBl {
    /**
     * 根据Id查找cardId
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

    double getBalance(int useId);
}
