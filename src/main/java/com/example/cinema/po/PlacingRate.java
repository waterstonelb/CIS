package com.example.cinema.po;

/**
 * @author yzh
 * @date 2019/5/14 ?:?? AM
 */
public class PlacingRate {
    private Double placingrate;
    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private String name;

    public Double getplacingrate() {
        return placingrate;
    }

    public void setplacingrate(double placingrate) {
        this.placingrate = placingrate;
    }

    public String getname () {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}