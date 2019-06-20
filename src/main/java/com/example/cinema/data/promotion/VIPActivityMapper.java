package com.example.cinema.data.promotion;


import com.example.cinema.po.VIPAtivity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * VIP卡种类相关操作
 */
@Mapper
public interface VIPActivityMapper {
    /**
     * 返回所有vip卡种类
     * @return
     */
    List<VIPAtivity> getCards();
    
    /**
     * 按照id查找VIP卡种类
     * @param cardId
     * @return
     */
    VIPAtivity getCardById(int cardId);

    /**
     * 添加新的VIP卡种类
     * @param vipAtivity
     * @return
     */
    int addNewCard(VIPAtivity vipAtivity);

    /**
     * 更新对应vip卡种类
     * @param vipAtivity
     * @return
     */
    int updataVIPActivity(VIPAtivity vipAtivity);

    /**
     * 将对id的卡种类标记为失效
     * @param id
     */
    int changeStatusToInvalid(int id);

    /**
     * 将对id的卡种类标记为有效
     * @param id
     * @return
     */
    int changeStatusToValid(int id);

}
