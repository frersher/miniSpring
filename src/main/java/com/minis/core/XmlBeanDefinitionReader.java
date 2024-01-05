package com.minis.core;

import com.minis.BeanDefinition;
import com.minis.BeanFactory;
import com.minis.Resource;
import org.dom4j.Element;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/12/22
 **/
public class XmlBeanDefinitionReader {
    BeanFactory factory;


    public  XmlBeanDefinitionReader(BeanFactory beanFactory){
        this.factory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource){
            while(resource.hasNext()){
                Element element = (Element) resource.next();
                String beanId = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition definition = new BeanDefinition(beanId, className);
                this.factory.registerBeanDefinition(definition);
            }
    }
}