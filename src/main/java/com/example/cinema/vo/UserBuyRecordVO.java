package com.example.cinema.vo;

import com.example.cinema.po.UserBuyRecord;

public class UserBuyRecordVO {
    private Double realPrice;
    private String time;
    private String name;

    public UserBuyRecordVO(UserBuyRecord userBuyRecord){
        this.realPrice = userBuyRecord.getrealPrice();
        this.time = userBuyRecord.gettime();
        this.name = userBuyRecord.getName();
    }
    public Double getrealPrice() {
        return realPrice;
    }

    public void setrealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
