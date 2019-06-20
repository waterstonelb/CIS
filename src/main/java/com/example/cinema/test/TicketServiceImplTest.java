package com.example.cinema.test;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.vo.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CinemaApplication.class)
@Transactional
public class TicketServiceImplTest {

    @Autowired
    TicketService ticketService;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    private TicketForm buildTicketForm(int userId,int scheduleId,int row,int col){
        TicketForm ticketForm = new TicketForm();
        List<SeatForm> ls = new ArrayList<>();
        SeatForm s = new SeatForm();
        s.setColumnIndex(col);
        s.setRowIndex(row);
        ls.add(s);
        ticketForm.setScheduleId(scheduleId);
        ticketForm.setSeats(ls);
        ticketForm.setUserId(userId);
        return ticketForm;
    }

    private TicketBuyForm buildTicketBuyForm(int userId, int couponId, int ticket){
        TicketBuyForm ticketBuyForm=new TicketBuyForm();
        List<Integer> ticketId=new ArrayList<>();
        ticketId.add(ticket);
        ticketBuyForm.setTicketId(ticketId);
        ticketBuyForm.setCouponId(couponId);
        ticketBuyForm.setUserId(userId);
        return ticketBuyForm;
    }


    /**
     * 正常买票
     */
    @Test
    public void addTicket() {
        TicketWithCouponVO ticketWithCouponVO = (TicketWithCouponVO)ticketService.addTicket(buildTicketForm(11,83,10,10)).getContent();
        assertEquals(55,(int)ticketWithCouponVO.getTotal());
        assertNotNull(ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10));
        assertEquals(0,ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10).getState());
    }

    /**
     * 在买票时发现票刚刚被人买走
     */
    @Test
    public void addTicket_2() {
        ticketService.addTicket(buildTicketForm(11,83,10,10));
        assertEquals("锁座失败！",ticketService.addTicket(buildTicketForm(10,83,10,10)).getMessage());
        assertEquals(11,ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10).getUserId());
    }

    /*
     *测试
     */
    @Test
    public void addTicket_3(){
        TicketWithCouponVO ticketWithCouponVO = (TicketWithCouponVO)ticketService.addTicket(buildTicketForm(11,85,10,10)).getContent();
        assertEquals(100,(int)ticketWithCouponVO.getTotal());
        assertNotNull(ticketMapper.selectTicketByScheduleIdAndSeat(85,10,10));
        assertEquals(0,ticketMapper.selectTicketByScheduleIdAndSeat(85,10,10).getState());
    }

    /*
    测试购票
     */
    @Test
    public void completeTicket() {
        TicketWithCouponVO ticketWithCouponVO=(TicketWithCouponVO)ticketService.addTicket(buildTicketForm(11,85,1,1)).getContent();
        assertEquals(1,ticketService.completeTicket(buildTicketBuyForm(11,1,ticketWithCouponVO.getTicketVOList().get(0).getId())).getContent());
    }

    @Test
    public void completeTicket_2(){
        TicketWithCouponVO ticketWithCouponVO=(TicketWithCouponVO)ticketService.addTicket(buildTicketForm(12,85,1,1)).getContent();
        assertEquals(1,ticketService.completeTicket(buildTicketBuyForm(12,1,ticketWithCouponVO.getTicketVOList().get(0).getId())).getContent());
    }

    @Test
    public void completeByVIPCard() {
        TicketWithCouponVO ticketWithCouponVO=(TicketWithCouponVO)ticketService.addTicket(buildTicketForm(10,85,1,2)).getContent();
        assertEquals(17712.2,ticketService.completeByVIPCard(buildTicketBuyForm(10,1,ticketWithCouponVO.getTicketVOList().get(0).getId())).getContent());
    }

    @Test
    public void completeByVIPCard_2() {
        TicketWithCouponVO ticketWithCouponVO=(TicketWithCouponVO)ticketService.addTicket(buildTicketForm(10,85,1,3)).getContent();
        assertEquals(17708.2,ticketService.completeByVIPCard(buildTicketBuyForm(10,0,ticketWithCouponVO.getTicketVOList().get(0).getId())).getContent());
    }
}