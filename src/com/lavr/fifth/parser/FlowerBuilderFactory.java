package com.lavr.fifth.parser;

/**
 * Created by 123 on 21.11.2016.
 */
public class FlowerBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractFlowerBuilder createFlowerBuilder (String typeParser) throws ParserException{
        try{
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowerDOMBuilder();
            case STAX:
                return new FlowerSTAXBuilder();
            case SAX:
                return new FlowerSAXBuilder();
            default:
                throw new ParserException ("No such parser"+type.getDeclaringClass()+" is defined");
        }
        }catch (IllegalArgumentException|NullPointerException e){
            throw new ParserException("Wrong parser type", e);
        }
    }
}
