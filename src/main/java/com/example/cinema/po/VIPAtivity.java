package com.example.cinema.po;

public class VIPAtivity {

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

    /**
     * 是否失效：0为失效，1为未失效
     */
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
