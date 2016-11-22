package com.lavr.fifth.entity;

/**
 * Created by 123 on 20.11.2016.
 */
public class GrowingTips{
    private Integer temperature;
    private Boolean photophilous;
    private Integer wateringAmount;

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Boolean getPhotophilous() {
        return photophilous;
    }

    public void setPhotophilous(Boolean photophilous) {
        this.photophilous = photophilous;
    }

    public Integer getWateringAmount() {
        return wateringAmount;
    }

    public void setWateringAmount(Integer wateringAmount) {
        this.wateringAmount = wateringAmount;
    }

    @Override
    public String toString() {
        return  ", temperature=" + temperature +" C"+
                ", photophilous=" + photophilous +
                ", wateringAmount=" + wateringAmount+" ml";
    }
}
