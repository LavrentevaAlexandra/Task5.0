package com.lavr.fifth.entity;

/**
 * Created by 123 on 20.11.2016.
 */
public class PoisonousFlower extends Flower {
    private String poisonousPart;

    public String getPoisonousPart() {
        return poisonousPart;
    }

    public void setPoisonousPart(String poisonousPart) {
        this.poisonousPart = poisonousPart;
    }

    @Override
    public String toString() {
        return  super.toString()+", poisonousPart=" + poisonousPart;
    }
}
