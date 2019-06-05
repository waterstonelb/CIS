package com.example.cinema.vo;

import com.example.cinema.po.VIPAtivity;

public class VIPActivityVO {

    /**
     * 会员卡种类id
     */
    private int id;

    /**
     * 会员卡名字
     */
    private String cardName;

    /**
     * 会员卡价格
     */
    private double cardPrice;

    /**
     * 满减金额的满
     */
    private double targetAmount;

    /**
     * 满减金额的减
     */
    private double discountAmount;

    /**
     * 购票折扣
     */
    private float discount;

    public VIPActivityVO(VIPAtivity vipAtivity){
        this.id=vipAtivity.getId();
        this.targetAmount=vipAtivity.getTargetAmount();
        this.discountAmount=vipAtivity.getDiscountAmount();
        this.discount=vipAtivity.getDiscount();
        this.cardName=vipAtivity.getCardName();
        this.cardPrice=vipAtivity.getCardPrice();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public double getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
