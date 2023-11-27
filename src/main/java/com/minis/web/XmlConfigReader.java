package com.minis.web;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/9/1
 **/
public class XmlConfigReader {
    public XmlConfigReader(){

    }

    public Map<String,MappingValue> loadConfig(Resource res){
        Map<String, MappingValue> mappings = new HashMap<>();

        while (res.hasNext()){
            Element element = (Element) res.next();
            String id = element.attributeValue("id");
            String aClass = element.attributeValue("class");
            String beanMethod = element.attributeValue("value");

            mappings.put(id, new MappingValue(id, aClass, beanMethod));
        }
        return mappings;
    }
}