package com.lavr.fifth.parser;

import com.lavr.fifth.entity.Flower;
import com.lavr.fifth.entity.FlowerEnum;
import com.lavr.fifth.entity.PoisonousFlower;
import com.lavr.fifth.entity.RareFlower;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 123 on 20.11.2016.
 */
public class FlowerHandler extends DefaultHandler {
    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> withText;

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public FlowerHandler() {
        flowers=new HashSet<>();
        withText=EnumSet.range(FlowerEnum.ID,FlowerEnum.QUANTITY);
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("poisonous-flower".equals(localName) || "rare-flower".equals(localName)) {
            if("poisonous-flower".equals(localName)){
                current = new PoisonousFlower();
            }else{
                current=new RareFlower();
            }
            current.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setName(attrs.getValue(1));
            } else {
                current.setName("no name");
            }
        }else {
            FlowerEnum temp = FlowerEnum.findByValue(localName);
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if ("poisonous-flower".equals(localName) || "rare-flower".equals(localName)) {
            flowers.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case SOIL:
                    current.setSoil(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case STEM_COLOUR:
                    current.getVisualParameters().setStemColour(s);
                    break;
                case LEAF_COLOUR:
                    current.getVisualParameters().setLeafColour(s);
                    break;
                case AVERAGE_SIZE:
                    current.getVisualParameters().setAverageSize(Double.parseDouble(s));
                    break;
                case TEMPERATURE:
                    current.getGrowingTips().setTemperature(Integer.parseInt(s));
                    break;
                case PHOTOPHILOUS:
                    current.getGrowingTips().setPhotophilous(Boolean.parseBoolean(s));
                    break;
                case WATER:
                    current.getGrowingTips().setWateringAmount(Integer.parseInt(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(s);
                    break;
                case POISONOUS_PART:
                    ((PoisonousFlower)current).setPoisonousPart(s);
                    break;
                case QUANTITY:
                    ((RareFlower)current).setQuantity(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
