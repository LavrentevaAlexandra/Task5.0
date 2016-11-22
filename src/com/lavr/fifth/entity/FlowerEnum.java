package com.lavr.fifth.entity;

import java.util.Arrays;

/**
 * Created by 123 on 20.11.2016.
 */
public enum FlowerEnum {
    FLOWERS("flowers"),
    RARE("rare-flower"),
    VISUAL_PARAMETERS("visual-parameters"),
    GROWING_TIPS("growing-tips"),
    POISONOUS("poisonous-flower"),
    ID("id"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    STEM_COLOUR("stem-colour"),
    LEAF_COLOUR("leaf-colour"),
    AVERAGE_SIZE("average-size"),
    TEMPERATURE("temperature"),
    PHOTOPHILOUS("photophilous"),
    WATER("watering-amount"),
    MULTIPLYING("multiplying"),
    POISONOUS_PART("poisonous-part"),
    QUANTITY("quantity");

    private String value;


    FlowerEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public static FlowerEnum findByValue(String value){
        FlowerEnum e[]=values();
        for (FlowerEnum current : values()) {
            if(current.getValue().equals(value)){
                return current;
            }
        }
        return null;
    }
}
