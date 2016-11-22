package com.lavr.fifth.entity;

/**
 * Created by 123 on 20.11.2016.
 */
public class RareFlower extends Flower {
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString()+", quantity=" + quantity;
    }
}
