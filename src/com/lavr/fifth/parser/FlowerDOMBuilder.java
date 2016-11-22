package com.lavr.fifth.parser;

import com.lavr.fifth.entity.*;
import com.lavr.fifth.parser.AbstractFlowerBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Created by 123 on 20.11.2016.
 */
public class FlowerDOMBuilder extends AbstractFlowerBuilder {
    private static final Logger LOG= LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public FlowerDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Parser configuration exception: " + e);
        }
    }



    public void buildSetFlowers(String fileName) throws ParserException{
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("rare-flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
            flowersList = root.getElementsByTagName("poisonous-flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException e) {
            throw new ParserException("File error or I/O error in DOM parser " , e);
        } catch (SAXException e) {
            throw new ParserException("Parsing failure in DOM parser " , e);
        }
    }
    private Flower buildFlower(Element flowerElement) {
        Flower flower;
        if("rare-flower".equals(flowerElement.getTagName())){
            flower=new RareFlower();
            ((RareFlower)flower).setQuantity(Integer.parseInt(getElementTextContent(flowerElement, "quantity")));
        }else {
            flower=new PoisonousFlower();
            ((PoisonousFlower)flower).setPoisonousPart(getElementTextContent(flowerElement, "poisonous-part"));

        }
        flower.setId(flowerElement.getAttribute("id"));
        String name=flowerElement.getAttribute("name");
        if(name!=null){
            flower.setName(name);
        }else{
            flower.setName("no name");
        }
        flower.setSoil(getElementTextContent(flowerElement, "soil"));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));
        flower.setMultiplying(getElementTextContent(flowerElement, "multiplying"));

        VisualParameters visualParameters = flower.getVisualParameters();
        Element vpElement = (Element) flowerElement.getElementsByTagName("visual-parameters").item(0);
        visualParameters.setStemColour(getElementTextContent(vpElement, "stem-colour"));
        visualParameters.setLeafColour(getElementTextContent(vpElement, "leaf-colour"));
        visualParameters.setAverageSize(Double.parseDouble(getElementTextContent(vpElement, "average-size")));

        GrowingTips growingTips = flower.getGrowingTips();
        Element gtElement = (Element) flowerElement.getElementsByTagName("growing-tips").item(0);
        growingTips.setTemperature(Integer.parseInt(getElementTextContent(gtElement, "temperature")));
        growingTips.setPhotophilous(Boolean.parseBoolean(getElementTextContent(gtElement, "photophilous")));
        growingTips.setWateringAmount(Integer.parseInt(getElementTextContent(gtElement, "watering-amount")));

        return flower;
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return  node.getTextContent();
    }
}
