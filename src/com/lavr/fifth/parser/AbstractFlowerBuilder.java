package com.lavr.fifth.parser;

import com.lavr.fifth.entity.Flower;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 123 on 21.11.2016.
 */
public abstract class AbstractFlowerBuilder {
    protected Set<Flower> flowers;
    public AbstractFlowerBuilder() {
        flowers = new HashSet<Flower>();
    }
    public AbstractFlowerBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }
    public Set<Flower> getFlowers() {
        return flowers;
    }
    abstract public void buildSetFlowers(String fileName) throws ParserException;
}
