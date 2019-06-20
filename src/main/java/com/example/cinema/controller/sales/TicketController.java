package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketBuyForm;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.TicketBuyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody TicketBuyForm TicketBuyForm){
        return ticketService.completeByVIPCard(TicketBuyForm);
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }
    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody TicketBuyForm ticketBuyForm){
        return ticketService.completeTicket(ticketBuyForm);
    }
    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId,@RequestParam String id){
        return ticketService.getBySchedule(scheduleId,Integer.parseInt(id));
    }
    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestBody List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }
    @PostMapping("/issue")
    public ResponseVO issueTicket(@RequestBody int ticketId){
        return ticketService.issueTicket(ticketId);
    }





}
