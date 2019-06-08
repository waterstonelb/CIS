package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;


public interface VIPActivityService{
    /**
     * 查找所有的vip卡
     * @return List<vip_activity>
     */
    ResponseVO getCards();

    ResponseVO getCardById(int cardId);

    /**
     * 查找所有有效卡
     * @return
     */
    ResponseVO getValidVIPCards();

    /**
     * 添加新卡
     * @param vipActivityForm
     * @return
     */
    ResponseVO addNewCard(VIPActivityForm vipActivityForm);
    /**
     * 更新对应vip卡
     * @param vipActivityForm
     * @return
     */
    ResponseVO updataVIPActivity(VIPActivityForm vipActivityForm);

    /**
     * 将对id的卡标记为失效
     * @param id
     * @return
     */
    ResponseVO changeStatusToInvalid(int id);

    /**
     * 将对id的卡标记为有效
     * @param id
     * @return
     */
    ResponseVO changeStatusToValid(int id);
}