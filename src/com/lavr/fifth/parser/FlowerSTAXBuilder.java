package com.lavr.fifth.parser;

import com.lavr.fifth.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 * Created by 123 on 20.11.2016.
 */
public class FlowerSTAXBuilder extends AbstractFlowerBuilder {
    private static final Logger LOG= LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public FlowerSTAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    public void buildSetFlowers(String fileName) throws ParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerEnum.findByValue(name) == FlowerEnum.POISONOUS || FlowerEnum.findByValue(name) == FlowerEnum.RARE) {
                        Flower flower = buildFlower(reader, name);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            throw new ParserException("StAX parsing error! ", ex);
        } catch (FileNotFoundException ex) {
            throw new ParserException("File " + fileName + " not found! " , ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Flower buildFlower(XMLStreamReader reader, String flowerType) throws ParserException {
        Flower flower;
        if ("rare-flower".equals(flowerType)) {
            flower = new RareFlower();
        } else {
            flower = new PoisonousFlower();
        }
        flower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getValue()));
        String name = FlowerEnum.NAME.getValue();
        if (name != null) {
            flower.setName(reader.getAttributeValue(null, name));
        } else {
            flower.setName("no name");
        }
        String temp;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        temp = reader.getLocalName();
                        switch (FlowerEnum.findByValue(temp)) {
                            case SOIL:
                                flower.setSoil(getXMLText(reader));
                                break;
                            case ORIGIN:
                                flower.setOrigin(getXMLText(reader));
                                break;
                            case MULTIPLYING:
                                flower.setMultiplying(getXMLText(reader));
                                break;
                            case VISUAL_PARAMETERS:
                                flower.setVisualParameters(getXMLVisualParameters(reader));
                                break;
                            case GROWING_TIPS:
                                flower.setGrowingTips(getXMLGrowingTips(reader));
                                break;
                            case POISONOUS_PART:
                                ((PoisonousFlower) flower).setPoisonousPart(getXMLText(reader));
                                break;
                            case QUANTITY:
                                ((RareFlower) flower).setQuantity(Integer.parseInt(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        temp = reader.getLocalName();
                        if (FlowerEnum.findByValue(temp) == FlowerEnum.POISONOUS || FlowerEnum.findByValue(temp) == FlowerEnum.RARE) {
                            return flower;
                        }
                        break;
                }
            }
            return flower;
        } catch (XMLStreamException e) {
            throw new ParserException("Unknown element in tag flower");
        }
    }

    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws ParserException {
        VisualParameters visualParameters = new VisualParameters();
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerEnum.findByValue(name)) {
                            case LEAF_COLOUR:
                                visualParameters.setLeafColour(getXMLText(reader));
                                break;
                            case STEM_COLOUR:
                                visualParameters.setStemColour(getXMLText(reader));
                                break;
                            case AVERAGE_SIZE:
                                visualParameters.setAverageSize(Double.parseDouble(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerEnum.findByValue(name) == FlowerEnum.VISUAL_PARAMETERS) {
                            return visualParameters;
                        }
                        break;
                }
            }
            return visualParameters;
        } catch (XMLStreamException e) {
            throw new ParserException("Unknown element in tag Visual Parameters", e);
        }
    }

    private GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws ParserException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerEnum.findByValue(name)) {
                            case TEMPERATURE:
                                growingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                                break;
                            case PHOTOPHILOUS:
                                growingTips.setPhotophilous(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case WATER:
                                growingTips.setWateringAmount(Integer.parseInt(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerEnum.findByValue(name) == FlowerEnum.GROWING_TIPS) {
                            return growingTips;
                        }
                        break;
                }
            }
            return growingTips;
        } catch (XMLStreamException e) {
            throw new ParserException("Unknown element in tag Growing Tips");

        }
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

