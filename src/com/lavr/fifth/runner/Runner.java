package com.lavr.fifth.runner;

import com.lavr.fifth.parser.AbstractFlowerBuilder;
import com.lavr.fifth.parser.FlowerBuilderFactory;
import com.lavr.fifth.parser.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by 123 on 20.11.2016.
 */
public class Runner {
    private static final String FILE_NAME="files/flowers.xml";
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {
        FlowerBuilderFactory flowerFactory = new FlowerBuilderFactory();
        try {
            AbstractFlowerBuilder builder = flowerFactory.createFlowerBuilder("SAX");
            builder.buildSetFlowers(FILE_NAME);
            System.out.println(builder.getFlowers());
        }catch (ParserException e){
            LOG.error(e);
        }
    }
}
