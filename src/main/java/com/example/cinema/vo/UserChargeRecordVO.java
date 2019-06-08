package com.example.cinema.vo;

import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;

public class UserChargeRecordVO {
    private Double chargeNum;
    private String time;

    public UserChargeRecordVO(UserChargeRecord userChargeRecord){
        this.chargeNum = userChargeRecord.getchargeNum();
        this.time = userChargeRecord.gettime();
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
