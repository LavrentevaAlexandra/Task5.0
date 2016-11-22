package com.lavr.fifth.entity;

/**
 * Created by 123 on 20.11.2016.
 */
public class Flower {
    private String id;
    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private String multiplying;

    public Flower() {
        visualParameters=new VisualParameters();
        growingTips=new GrowingTips();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name=" + name +
                ", soil=" + soil +
                ", origin=" + origin +
                 visualParameters +
                 growingTips +
                ", multiplying=" + multiplying;
    }
}

