package com.example.cinema.po;

import java.sql.Timestamp;

public class RefundPolicy {
    private int id;
    private Float refund_day;
    private Float refund_hour;
    private Timestamp issue_time;
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setTimestamp(int id){
        this.id = id;
    }
    public Timestamp getTimestamp(){
        return issue_time;
    }
    public void setRefund_day(Float refund_day){
        this.refund_day=refund_day;
    }
    public Float getRefund_day(){
        return refund_day;
    }
    public void setRefund_hour(Float refund_hour){
        this.refund_hour=refund_hour;
    }
    public Float getRefund_hour(){
        return refund_hour;
    }
}