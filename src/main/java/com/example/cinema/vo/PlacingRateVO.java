package com.example.cinema.vo;

import com.example.cinema.po.PlacingRate;

public class PlacingRateVO {
    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private Double placingrate;
    private String name;

    public PlacingRateVO(PlacingRate placingrate){
        this.placingrate = placingrate.getplacingrate();
        this.name = placingrate.getname();
    }

    public Double getPlacingrate() {
        return placingrate;
    }

    public void setPlacingrate(Double placingrate) {
        this.placingrate = placingrate;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
