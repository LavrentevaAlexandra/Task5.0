package com.lavr.fifth.entity;

/**
 * Created by 123 on 20.11.2016.
 */
public class VisualParameters{
    private String stemColour;
    private String leafColour;
    private Double averageSize;

    public String getStemColour() {
        return stemColour;
    }

    public void setStemColour(String stemColour) {
        this.stemColour = stemColour;
    }

    public String getLeafColour() {
        return leafColour;
    }

    public void setLeafColour(String leafColour) {
        this.leafColour = leafColour;
    }

    public Double getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(Double averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public String toString() {
        return  ", stemColour=" + stemColour +
                ", leafColour=" + leafColour +
                ", averageSize=" + averageSize+" sm ";
    }
}
