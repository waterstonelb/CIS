package com.example.cinema.vo;

import java.sql.Timestamp;

public class RefundPolicyVO {
    private Float refund_day;
    private Float refund_hour;
    private Timestamp issue_time;

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
    public Timestamp getIssue_time(){
        return issue_time;
    }
    public void setIssue_time(Timestamp issue_time){
        this.issue_time = issue_time;
    }

}