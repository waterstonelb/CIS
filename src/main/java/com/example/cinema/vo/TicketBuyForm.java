package com.example.cinema.vo;

import java.util.List;

public class TicketBuyForm {
    private List<Integer> ticketId;

    private int userId;

    private int couponId;

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setTicketId(List<Integer> ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }



}
