package com.example.cinema.blImpl.sales;

import com.example.cinema.blImpl.promotion.refund.RefundServiceForBl;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.schedule.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.activity.ActivityServiceForBl;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.blImpl.promotion.vipactivity.VIPActivityServiceForBl;
import com.example.cinema.blImpl.promotion.vipservice.VIPServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    HallServiceForBl hallServiceForBl;
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
                        ticketForm.getSeats().get(i).getColumnIndex(), ticketForm.getSeats().get(i).getRowIndex(), 0,
                        0);
                Ticket conflictTicket = ticketMapper.selectTicketByScheduleIdAndSeat(ticket.getScheduleId(),
                        ticket.getColumnIndex(), ticket.getRowIndex());
                        
                if (conflictTicket != null && conflictTicket.getState() != 2
                        && conflictTicket.getUserId() != ticket.getUserId()) {//新增的票和数据库有冲突，锁座失败
                    return ResponseVO.buildFailure("锁座失败！");
                }
                ticketList.add(ticket);
            }
            for (Ticket t : ticketList) {//在数据库中插入票（状态为锁座）
                Ticket tmpT = ticketMapper.selectTicketByScheduleIdAndSeat(t.getScheduleId(), t.getColumnIndex(),
                        t.getRowIndex());
                if (tmpT == null || tmpT.getState() != 0) {
                    ticketMapper.insertTicket(t);
                }
            }

            // 获取totals数据，totals数据为扣款金额
            //double totals=100;
            ScheduleItem scheduleItem = scheduleServiceForBl.getScheduleItemById(ticketForm.getScheduleId());
            double totals = scheduleItem.getFare() * ticketForm.getSeats().size();
            List<TicketVO> ticketVOList = new ArrayList<TicketVO>();
            //锁座
            for (int i = 0; i < ticketForm.getSeats().size(); i++) {
                Ticket nticket = ticketMapper.selectTicketByScheduleIdAndSeat(ticketForm.getScheduleId(),
                        ticketForm.getSeats().get(i).getColumnIndex(), ticketForm.getSeats().get(i).getRowIndex());
                ticketVOList.add(nticket.getVO());
            }
            // 获取coupon数据
            /*
            List<Coupon> rescoupons = new ArrayList<>();
            Coupon coupon=new Coupon();
            coupon.setDescription("test");
            coupon.setLevel(0);
            coupon.setDiscountAmount(10);
            coupon.setName("test");
            coupon.setId(1);
             */
            @SuppressWarnings("unchecked")
            List<Coupon> coupons = couponServiceForBl.getCouponByUserId(ticketForm.getUserId());// 获取activity数据
            List<Coupon> rescoupons = new ArrayList<>();
            for (Coupon coupon : coupons) {
                if (coupon.getTargetAmount() < totals)
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
    public ResponseVO completeTicket(TicketBuyForm ticketBuyForm) {// 校验优惠券和根据优惠活动赠送优惠券
        try {
            double total = useCoupon(ticketBuyForm.getTicketId().get(0), ticketBuyForm.getCouponId(),
                    ticketBuyForm.getTicketId().size());// 扣款金额，暂不处理
            total = total / ticketBuyForm.getTicketId().size();
            for (Integer ticketId : ticketBuyForm.getTicketId()) {
                ticketMapper.updateTicketState(ticketId, 1);
                ticketMapper.setRealPay(total, ticketId);
            }
            //测试用数据
            //int numOfCoupon = 2;
            int numOfCoupon = userGetCoupons(ticketBuyForm.getTicketId().get(0));
            return ResponseVO.buildSuccess(numOfCoupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("购票失败！");
        }
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId, int userId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleServiceForBl.getScheduleItemById(scheduleId);
            Hall hall = hallServiceForBl.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                if (ticket.getState() == 1 || ticket.getState() == 3)
                    seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;// 已完成
                else if (ticket.getState() == 0) {
                    if (ticket.getUserId() == userId)
                        seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 2;// 锁座
                    else
                        seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;// 已完成
                }
            });
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
                if (ticket.getState() == 1 || ticket.getState() == 3) {
                    TicketWithScheduleVO ticketWithScheduleVO = new TicketWithScheduleVO();
                    ticketWithScheduleVO.setId(ticket.getId());
                    ticketWithScheduleVO.setUserId(ticket.getUserId());
                    ticketWithScheduleVO.setSchedule(scheduleServiceForBl.getScheduleItemById(ticket.getScheduleId()));
                    ticketWithScheduleVO.setColumnIndex(ticket.getColumnIndex());
                    ticketWithScheduleVO.setRowIndex(ticket.getRowIndex());
                    ticketWithScheduleVO.setState(ticket.getState() == 1 ? "已完成" : "已出票");
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
    public ResponseVO completeByVIPCard(TicketBuyForm TicketBuyForm) {
        try {
            //测试用数据
            //double totals = 100;
            double totals = useCoupon(TicketBuyForm.getTicketId().get(0), TicketBuyForm.getCouponId(),
                    TicketBuyForm.getTicketId().size());
            VIPAtivity vipAtivity = vipActivityServiceForBl
                    .getVIPActivity(vipServiceForBl.getCardId(TicketBuyForm.getUserId()));
            totals = totals * vipAtivity.getDiscount();
            double total = totals / TicketBuyForm.getTicketId().size();

            for (Integer ticketId : TicketBuyForm.getTicketId()) {
                ticketMapper.updateTicketState(ticketId, 1);
                ticketMapper.setRealPay(total, ticketId);
            }
            vipServiceForBl.buyTicket(TicketBuyForm.getUserId(), totals);
            userGetCoupons(TicketBuyForm.getTicketId().get(0));
            double balance = vipServiceForBl.getBalance(TicketBuyForm.getUserId());
            return ResponseVO.buildSuccess(balance);
        } catch (Exception e) {
            return ResponseVO.buildFailure("vip购票失败");
        }
    }

    /**
     * 根据ticketID退票
     */
    @Override
    @Transactional
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            int userId = ticketMapper.selectTicketById(id.get(0)).getUserId();
            double totals = 0;
            RefundPolicyVO rVO = refunServiceForBl.getRefundPolicyVO();
            long thisTime = new Date().getTime();
            for (int Id : id) {//完成退票操作
                Ticket tmpTicket = ticketMapper.selectTicketById(Id);
                Date scheduleStart = scheduleServiceForBl.getScheduleItemById(tmpTicket.getScheduleId()).getStartTime();
                if((scheduleStart.getTime()- thisTime)>=(long)24*3600*1000)
                    totals+=tmpTicket.getRealPay()*rVO.getRefund_day();
                else if((scheduleStart.getTime()- thisTime)>=(long)3600*1000)
                    totals+=tmpTicket.getRealPay()*rVO.getRefund_hour();
                ticketMapper.updateTicketState(Id,2);
            }
            //根据用户性质的不同返回不通VO
            if(vipServiceForBl.getCardId(userId)!=-1){
                vipServiceForBl.returnTicket(userId, totals+vipServiceForBl.getBalance(userId));
                System.out.println("会员");
                return ResponseVO.buildSuccess(totals);
            }
            else{
                System.out.println("普通用户");
                return ResponseVO.buildSuccess(totals);
            }
        } catch (Exception e) {
            return ResponseVO.buildFailure("退票失败");
        }
    }

    /**
     * 优惠券发放
     * 
     * @param ticketId
     * @return
     */
    private int userGetCoupons(int ticketId) {
        Ticket ticket = ticketMapper.selectTicketById(ticketId);
        int count=0;
        int scheduleId = ticket.getScheduleId();
        int movieId = scheduleServiceForBl.getScheduleItemById(scheduleId).getMovieId();
        List<Activity> activities = activityServiceForBl.getActivitiesByMovie(movieId);
        for (Activity avtivity : activities) {
            if (avtivity.getCoupon().getLevel() == 0) {
                count++;
                couponServiceForBl.insertCouponUser(avtivity.getCoupon().getId(), ticket.getUserId());
            }
        }
        return count;
    }

    /**
     *
     * @param ticketId
     * @param couponId
     * @return 使用优惠券后总金额
     */
    private double useCoupon(int ticketId, int couponId, int numOfTicket) {
        try {
            Ticket ticket = ticketMapper.selectTicketById(ticketId);
            double oneMoive = scheduleServiceForBl.getScheduleItemById(ticket.getScheduleId()).getFare();
            List<Coupon> coupons = couponServiceForBl.getTicketCoupons(ticket.getUserId(), oneMoive * numOfTicket);
            boolean haveCoupon = false;
            for (Coupon coupon : coupons) {
                if (coupon.getId() == couponId) {
                    haveCoupon = true;
                    couponServiceForBl.deleteUserCoupon(ticket.getUserId(), couponId);
                    break;
                }
            }
            double total = haveCoupon
                    ? oneMoive * numOfTicket - couponServiceForBl.getCoupon(couponId).getDiscountAmount()
                    : oneMoive * numOfTicket;
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public ResponseVO issueTicket(int ticketId) {
        try {
            ticketMapper.updateTicketState(ticketId, 3);
            Ticket ticket = ticketMapper.selectTicketById(ticketId);
            TicketWithScheduleVO tScheduleVO = new TicketWithScheduleVO();
            tScheduleVO.setId(ticket.getId());
            tScheduleVO.setUserId(ticket.getUserId());
            tScheduleVO.setSchedule(scheduleServiceForBl.getScheduleItemById(ticket.getScheduleId()));
            tScheduleVO.setColumnIndex(ticket.getColumnIndex());
            tScheduleVO.setRowIndex(ticket.getRowIndex());
            tScheduleVO.setState("已出票");
            tScheduleVO.setTime(ticket.getTime());

            return ResponseVO.buildSuccess(tScheduleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("出票失败");
        }
    }
}
