package com.example.cinema.vo;

/**
 * Created by liying on 2019/4/14.
 */
public class VIPCardForm {

    /**
     * vip卡id
     */
    private int vipId;

    private int cardId;

    private double targetAmount;

    private double discountAmount;

    /**
     * 付款金额
     */
    private double amount;

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getCardId() {
        return cardId;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }


    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
