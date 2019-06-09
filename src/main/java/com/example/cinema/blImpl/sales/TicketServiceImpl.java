package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.management.RefundServiceForBl;
import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.bl.promotion.*;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.ActivityServiceForBl;
import com.example.cinema.blImpl.promotion.CouponServiceForBl;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponServiceForBl couponServiceForBl;
    @Autowired
    VIPServiceForBl vipServiceForBl;
    @Autowired
    VIPActivityServiceForBl vipActivityServiceForBl;
    @Autowired
    ActivityServiceForBl activityServiceForBl;
    @Autowired
    RefundServiceForBl refunServiceForBl;

    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        try {
            List<Ticket> ticketList = new ArrayList<>();
            for (int i = 0; i < ticketForm.getSeats().size(); i++) {
                Ticket ticket = new Ticket(ticketForm.getUserId(), ticketForm.getScheduleId(),
                        ticketForm.getSeats().get(i).getColumnIndex(), ticketForm.getSeats().get(i).getRowIndex(), 0,0);
                ticketList.add(ticket);
            }
            ticketMapper.insertTickets(ticketList);

            //获取totals数据
            ScheduleItem scheduleItem = scheduleService.getScheduleItemById(ticketForm.getScheduleId());
            double totals = scheduleItem.getFare() * ticketForm.getSeats().size();
            List<TicketVO> ticketVOList = new ArrayList<TicketVO>();
            for (int i = 0; i < ticketForm.getSeats().size(); i++) {
                Ticket nticket = ticketMapper.selectTicketByScheduleIdAndSeat(ticketForm.getScheduleId(), ticketForm.getSeats().get(i).getColumnIndex(), ticketForm.getSeats().get(i).getRowIndex());
                ticketVOList.add(nticket.getVO());
            }
            //获取coupon数据
            @SuppressWarnings("unchecked")
            List<Coupon> coupons = (List<Coupon>) couponService.getCouponsByUser(ticketForm.getUserId()).getContent();//获取activity数据
            List<Coupon> rescoupons=new ArrayList<>();
            for(Coupon coupon:coupons){
                if(coupon.getTargetAmount()<totals)
                    rescoupons.add(coupon);
            }
            TicketWithCouponVO twc = new TicketWithCouponVO();
            twc.setActivities(null);
            twc.setCoupons(rescoupons);
            twc.setTicketVOList(ticketVOList);
            twc.setTotal(totals);
            return ResponseVO.buildSuccess(twc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(TicketBuyForm ticketBuyForm) {//校验优惠券和根据优惠活动赠送优惠券
        try {
            double total=useCoupon(ticketBuyForm.getTicketId().get(0),ticketBuyForm.getCouponId(),ticketBuyForm.getTicketId().size());//扣款金额，暂不处理
            total=total/ticketBuyForm.getTicketId().size();
            for (Integer ticketId : ticketBuyForm.getTicketId()) {
                ticketMapper.updateTicketState(ticketId, 1);
                ticketMapper.setRealPay(total,ticketId);
            }
            int numOfCoupon=userGetCoupons(ticketBuyForm.getTicketId().get(0));
            return ResponseVO.buildSuccess(numOfCoupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("购票失败！");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket ->
                seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1
            );
            ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            List<TicketWithScheduleVO> ticketWithScheduleVOS = new ArrayList<>();
            for (Ticket ticket : ticketMapper.selectTicketByUser(userId)) {
                if (ticket.getState() == 1 || ticket.getState() == 2 || ticket.getState() == 3) {
                    TicketWithScheduleVO ticketWithScheduleVO=new TicketWithScheduleVO();
                    ticketWithScheduleVO.setId(ticket.getId());
                    ticketWithScheduleVO.setUserId(ticket.getUserId());
                    ticketWithScheduleVO.setSchedule(scheduleService.getScheduleItemById(ticket.getScheduleId()));
                    ticketWithScheduleVO.setColumnIndex(ticket.getColumnIndex());
                    ticketWithScheduleVO.setRowIndex(ticket.getRowIndex());
                    ticketWithScheduleVO.setState(ticket.getState()==1?"已完成":(ticket.getState()==2?"已失效":"已出票"));
                    ticketWithScheduleVO.setTime(ticket.getTime());

                    ticketWithScheduleVOS.add(ticketWithScheduleVO);
                }
            }
            return ResponseVO.buildSuccess(ticketWithScheduleVOS);
        } catch (Exception e) {
            return ResponseVO.buildFailure("用户查询已购票失败！");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(TicketVIPBuyForm ticketVIPBuyForm) {
        try{
            double totals=useCoupon(ticketVIPBuyForm.getTicketId().get(0),ticketVIPBuyForm.getCouponId(),ticketVIPBuyForm.getTicketId().size());
            VIPAtivity vipAtivity=vipActivityServiceForBl.getVIPActivity(vipServiceForBl.getCardId(ticketVIPBuyForm.getUserId()));
            totals=totals*vipAtivity.getDiscount();
            double total=totals/ticketVIPBuyForm.getTicketId().size();

            for (Integer ticketId : ticketVIPBuyForm.getTicketId()) {
                ticketMapper.updateTicketState(ticketId, 1);
                ticketMapper.setRealPay(total,ticketId);
            }
            vipServiceForBl.buyTicket(ticketVIPBuyForm.getUserId(),totals);
            userGetCoupons(ticketVIPBuyForm.getTicketId().get(0));
            double balance=vipServiceForBl.getBalance(ticketVIPBuyForm.getUserId());
            return ResponseVO.buildSuccess(balance);
        }catch (Exception e){
            return ResponseVO.buildFailure("vip购票失败");
        }
    }

    /**
     * 根据ticketID退票
     */
    @Override
    public ResponseVO cancelTicket(List<Integer> id) {//TODO: ，目前仅支持vip退票，普通用户退票会产生bug
        try {
            int userId = ticketMapper.selectTicketById(id.get(0)).getUserId();
            double totals = vipServiceForBl.getBalance(userId);
            RefundPolicyVO rVO = refunServiceForBl.getRefundPolicyVO();
            long thisTime = new Date().getTime();
            for (int Id : id) {
                Ticket tmpTicket = ticketMapper.selectTicketById(Id);
                Date scheduleStart = scheduleService.getScheduleItemById(tmpTicket.getScheduleId()).getStartTime();
                if((scheduleStart.getTime()- thisTime)>=(long)24*3600*1000)
                    totals+=tmpTicket.getRealPay()*rVO.getRefund_day();
                else if((scheduleStart.getTime()- thisTime)>=(long)3600*1000)
                    totals+=tmpTicket.getRealPay()*rVO.getRefund_hour();
                ticketMapper.updateTicketState(Id,2);
            }
            vipServiceForBl.returnTicket(userId, totals);
            return ResponseVO.buildSuccess(totals);
        }catch (Exception e){
            return ResponseVO.buildFailure("退票失败");
        }
    }

    /**
     * 优惠券发放
     * @param ticketId
     * @return
     */
    private int userGetCoupons(int ticketId ){
        Ticket ticket=ticketMapper.selectTicketById(ticketId);
        int scheduleId=ticket.getScheduleId();
        int movieId=scheduleService.getScheduleItemById(scheduleId).getMovieId();
        List<Activity> activities=activityServiceForBl.getActivitiesByMovie(movieId);
        for (Activity avtivity:activities) {
            couponService.issueCoupon(avtivity.getCoupon().getId(),ticket.getUserId());
        }
        return activities.size();
    }

    /**
     *
     * @param ticketId
     * @param couponId
     * @return 使用优惠券后总金额
     */
    private double useCoupon(int ticketId,int couponId,int numOfTicket){
        try {
            Ticket ticket = ticketMapper.selectTicketById(ticketId);
            double oneMoive = scheduleService.getScheduleItemById(ticket.getScheduleId()).getFare();
            List<Coupon> coupons=couponServiceForBl.getTicketCoupons(ticket.getUserId(),oneMoive*numOfTicket);
            boolean haveCoupon=false;
            for(Coupon coupon:coupons){
                if (coupon.getId()==couponId) {
                    haveCoupon = true;
                    couponServiceForBl.deleteUserCoupon(ticket.getUserId(), couponId);
                }
            }
            double total = haveCoupon?oneMoive * numOfTicket - couponServiceForBl.getCoupon(couponId).getDiscountAmount():oneMoive * numOfTicket;
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
