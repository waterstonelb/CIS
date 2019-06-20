package com.example.cinema.test;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.vo.SeatForm;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.TicketWithCouponVO;
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

    /**
     * 正常买票
     */
    @Test
    public void addTicket() {
        TicketWithCouponVO ticketWithCouponVO = (TicketWithCouponVO)ticketService.addTicket(buildTicketForm(11,83,10,10)).getContent();
        assertEquals((int)ticketWithCouponVO.getTotal(),100);
        assertNotNull(ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10));
        assertEquals(ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10).getState(),0);
    }

    /**
     * 在买票时发现票刚刚被人买走
     */
    @Test
    public void addTicket_2() {
        ticketService.addTicket(buildTicketForm(11,83,10,10));
        assertEquals(ticketService.addTicket(buildTicketForm(10,83,10,10)).getMessage(),"锁座失败！");
        assertEquals(ticketMapper.selectTicketByScheduleIdAndSeat(83,10,10).getUserId(),11);
    }

    @Test
    public void completeTicket() {

    }

    @Test
    public void completeByVIPCard() {
    }
}