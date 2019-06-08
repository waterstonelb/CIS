package com.example.cinema.vo;

import java.util.List;

public class TicketVIPBuyForm {

    private List<Integer> ticketId;
    private int couponId;
    private double totals;
    private int userId;
    private int cardId;

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public int getCouponId() {
        return couponId;
    }

    public double getTotals() {
        return totals;
    }

    public int getUserId() {
        return userId;
    }
}
