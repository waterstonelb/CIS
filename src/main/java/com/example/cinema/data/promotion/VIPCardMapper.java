package com.example.cinema.data.promotion;

import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.po.VIPCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liying on 2019/4/14.
 */
@Mapper
public interface VIPCardMapper {

    /**
     * 插入一张会员卡
     * @param vipCard
     * @return
     */
    int insertOneCard(VIPCard vipCard);

    /**
     * 查询会员卡
     * @param id
     * @return
     */
    VIPCard selectCardById(int id);

    /**
     * 更新会员卡余额
     * @param id
     * @param balance
     */
    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    /**
     * 通过用户Id查询会员卡
     * @param userId
     * @return
     */
    VIPCard selectCardByUserId(int userId);

    /**
     * 充钱
     * @param userChargeRecord
     */
    void insertUserCharge(UserChargeRecord userChargeRecord);

    /**
     * 升级会员卡
     * @param id
     * @param balance
     * @param cardId
     */
    void updateCard(@Param("id") int id,@Param("balance") double balance,@Param("cardId") int cardId);

}
