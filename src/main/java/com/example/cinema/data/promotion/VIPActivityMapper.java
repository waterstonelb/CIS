package com.example.cinema.data.promotion;


import com.example.cinema.po.VIPAtivity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VIPActivityMapper {
    /**
     * 返回所有vip卡
     * @return
     */
    List<VIPAtivity> getCards();
    
    /**
     * 按照id查找card
     * @param cardId
     * @return
     */
    VIPAtivity getCardById(int cardId);

    /**
     * 添加新卡
     * @param vipAtivity
     * @return
     */
    int addNewCard(VIPAtivity vipAtivity);

    /**
     * 更新对应vip卡
     * @param vipAtivity
     * @return
     */
    int updataVIPActivity(VIPAtivity vipAtivity);

    /**
     * 将对id的卡标记为失效
     * @param id
     */
    int changeStatusToInvalid(int id);

    /**
     * 将对id的卡标记为有效
     * @param id
     * @return
     */
    int changeStatusToValid(int id);

}
