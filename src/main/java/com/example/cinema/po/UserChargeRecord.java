package com.example.cinema.po;

/**
 * @author yzh
 * @date 2019/6/7 ???
 */
public class UserChargeRecord {
    private int userId;
    private Double chargeNum;
    private String time;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getchargeNum() {
        return chargeNum;
    }

    public void setchargeNum(Double chargeNum) {
        this.chargeNum = chargeNum;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

}