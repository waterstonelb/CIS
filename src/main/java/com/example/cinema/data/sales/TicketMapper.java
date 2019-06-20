package com.example.cinema.data.sales;

import com.example.cinema.po.Ticket;
import com.example.cinema.po.UserBuyRecord;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**电影票相关操作
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface TicketMapper {

    /**
     * 插入一张电影票
     * @param ticket
     * @return
     */
    int insertTicket(Ticket ticket);

    /**
     * 插入电影票
     * @param tickets
     * @return
     */
    int insertTickets(List<Ticket> tickets);

    /**
     * 删除电影票
     * @param ticketId
     */
    void deleteTicket(int ticketId);

    /**
     * 设置实际支付的钱
     * @param realPay
     * @param ticketId
     * @return
     */
    int setRealPay(double realPay,int ticketId);

    /**
     * 改变票的状态
     * @param ticketId
     * @param state
     */
    void updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    /**
     * 根据场次Id获取票
     * @param scheduleId
     * @return
     */
    List<Ticket> selectTicketsBySchedule(int scheduleId);

    /**
     * 根据场次Id以及座位号获取票
     * @param scheduleId
     * @param columnIndex
     * @param rowIndex
     * @return
     */
    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    /**
     * 根据票的id获取票
     * @param id
     * @return
     */
    Ticket selectTicketById(int id);

    /**
     * 根据用户id获取票
     * @param userId
     * @return
     */
    List<Ticket> selectTicketByUser(int userId);

    /**
     * 十五分钟后将锁座的票的状态改为失效
     */
    @Scheduled(cron = "0/1 * * * * ?")
    void cleanLockedTicket();

    /**
     * 将已完成支付的并且过期的票改为已出票状态
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    void cleanExpiredTicket();
}

