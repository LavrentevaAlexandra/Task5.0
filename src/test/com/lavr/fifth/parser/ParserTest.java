package test.com.lavr.fifth.parser;

import com.lavr.fifth.entity.Flower;
import com.lavr.fifth.parser.AbstractFlowerBuilder;
import com.lavr.fifth.parser.FlowerBuilderFactory;
import com.lavr.fifth.parser.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 123 on 21.11.2016.
 */
public class ParserTest {
    private static final String FILE_NAME="files/flowers.xml";
    private static final String FAIL_FILE_NAME="files/fail.xml";
    private static final Logger LOG= LogManager.getLogger();
    private FlowerBuilderFactory flowerFactory= new FlowerBuilderFactory();;


    @Test
    public void checkSAX(){
        try {
            AbstractFlowerBuilder builder = flowerFactory.createFlowerBuilder("SAX");
            builder.buildSetFlowers(FILE_NAME);
            for (Flower flower:builder.getFlowers()) {
                if(flower.toString().contains("null")){
                    Assert.fail();
                }
            }
        }catch (ParserException e){
            LOG.error(e);
        }
    }

    @Test
    public void checkSAXFail(){
        try {
            AbstractFlowerBuilder builder = flowerFactory.createFlowerBuilder("SAX");
            builder.buildSetFlowers(FAIL_FILE_NAME);
            for (Flower flower:builder.getFlowers()) {
                if(!flower.toString().contains("null")){
                    Assert.fail();
                }
            }
        }catch (ParserException e){
            LOG.error(e);
        }
    }

    @Test
    public void checkSTAX(){
        try {
            AbstractFlowerBuilder builder = flowerFactory.createFlowerBuilder("STAX");
            builder.buildSetFlowers(FILE_NAME);
            for (Flower flower:builder.getFlowers()) {
                if(flower.toString().contains("null")){
                    Assert.fail();
                }
            }
        }catch (ParserException e){
            LOG.error(e);
        }
    }

    @Test
    public void checkDOM(){
        try {
            AbstractFlowerBuilder builder = flowerFactory.createFlowerBuilder("DOM");
            builder.buildSetFlowers(FILE_NAME);
            for (Flower flower:builder.getFlowers()) {
                if(flower.toString().contains("null")){
                    Assert.fail();
                }
            }
        }catch (ParserException e){
            LOG.error(e);
        }
    }
}
