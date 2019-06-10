package com.example.cinema.blImpl.promotion.VIPActivity;

import com.example.cinema.po.VIPAtivity;

public interface VIPActivityServiceForBl {

    /**
     * bl层的获取card种类
     * @param cardId
     * @return
     */
    VIPAtivity getVIPActivity(int cardId);
}
