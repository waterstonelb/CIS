package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketBuyForm;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    /**
     * 通过VIP卡买票
     * @param TicketBuyForm
     * @return
     */
    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody TicketBuyForm TicketBuyForm){
        return ticketService.completeByVIPCard(TicketBuyForm);
    }

    /**
     * 锁座
     * @param ticketForm
     * @return
     */
    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }
    /**
     * 普通用户购票
     * @param ticketBuyForm
     * @return
     */
    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody TicketBuyForm ticketBuyForm){
        return ticketService.completeTicket(ticketBuyForm);
    }
    /**
     * 通过用户Id获取票
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    /**
     * 获取锁座信息
     * @param scheduleId
     * @param id
     * @return
     */
    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId,@RequestParam String id){
        return ticketService.getBySchedule(scheduleId,Integer.parseInt(id));
    }
    /**
     * 退票
     * @param ticketId
     * @return
     */
    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestBody List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }
    /**
     * 出票
     * @param ticketId
     * @return
     */
    @PostMapping("/issue")
    public ResponseVO issueTicket(@RequestBody int ticketId){
        return ticketService.issueTicket(ticketId);
    }





}
