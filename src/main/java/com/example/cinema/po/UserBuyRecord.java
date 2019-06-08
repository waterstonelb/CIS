package com.example.cinema.po;

/**
 * @author yzh
 * @date 2019/6/7 ???
 */
public class UserBuyRecord {
    private Double realPrice;
    private String time;
    private String name;

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
