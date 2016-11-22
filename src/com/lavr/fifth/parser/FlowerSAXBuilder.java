package com.lavr.fifth.parser;

import com.lavr.fifth.entity.Flower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by 123 on 20.11.2016.
 */
public class FlowerSAXBuilder extends AbstractFlowerBuilder{
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public FlowerSAXBuilder() {
        flowerHandler=new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            System.err.print("SAX parser exception: " + e);
        }
    }

    public void buildSetFlowers(String fileName) throws ParserException{
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new ParserException("SAX parser exception " ,e);
        } catch (IOException e) {
            throw new ParserException("IOException in SAX parser " ,e);
        }
        flowers = flowerHandler.getFlowers();
    }
}
