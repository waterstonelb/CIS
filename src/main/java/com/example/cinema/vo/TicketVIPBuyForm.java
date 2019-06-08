package com.example.cinema.vo;

import java.util.List;

public class TicketVIPBuyForm {

    private List<Integer> ticketId;
    private int couponId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    public void setTicketId(List<Integer> ticketId) {
        this.ticketId = ticketId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public int getCouponId() {
        return couponId;
    }


    public int getUserId() {
        return userId;
    }
}
