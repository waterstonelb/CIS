package com.example.cinema.bl.sales;

import com.example.cinema.vo.*;

import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     * 锁座【增加票但状态为未付款】
     *
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    /**
     * 完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券
     *
     * @para ticketBuyForm
     * @return
     */
    ResponseVO completeTicket(TicketBuyForm ticketBuyForm);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ResponseVO getBySchedule(int scheduleId,int userId);

    /**
     * 获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * 完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param ticketVIPBuyFrom
     * @return
     */
    ResponseVO completeByVIPCard(TicketBuyForm ticketVIPBuyFrom);

    /**
     * 取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param id
     * @return
     */
    ResponseVO cancelTicket(List<Integer> id);

    /**
     * 出票
     * @param ticketId
     * @return
     */
    ResponseVO issueTicket(int ticketId);

}
