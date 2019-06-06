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

    private double discountSmount;

    /**
     * 付款金额
     */
    private int amount;

    public int getCardId() {
        return cardId;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getDiscountSmount() {
        return discountSmount;
    }

    public void setDiscountSmount(double discountSmount) {
        this.discountSmount = discountSmount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
