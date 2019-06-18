package com.example.cinema.data.statistics;

import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author yzh
 * @date 2019/6/18 23:31 PM
 * 在数据库层面实现用户记录统计相关功能
 */
@Mapper
public interface UserStatisticsMapper {
	/**
     * 查询购买记录
     * @param id
     * @return
     */
    List<UserBuyRecord> SelectBuyRecord(@Param("id") int id);
    /**
     * 查询充值记录
     * @param id 
     * @param 
     * @return
     */
    List<UserChargeRecord> SelectChargeRecord(@Param("id") int id);
}