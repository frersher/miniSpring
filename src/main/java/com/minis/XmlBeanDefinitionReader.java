package com.minis;

import com.minis.beans.ArgumentValue;
import com.minis.beans.ArgumentValues;
import com.minis.beans.BeanDefinition;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.Resource;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class XmlBeanDefinitionReader {

  SimpleBeanFactory simpleBeanFactory;

  public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
    this.simpleBeanFactory = simpleBeanFactory;
  }

  public void loadBeanDefinitions(Resource resource) throws BeansException {
    while (resource.hasNext()) {
      Element element = (Element) resource.next();
      String id = element.attributeValue("id");
      String className = element.attributeValue("class");
      BeanDefinition beanDefinition = new BeanDefinition(id, className);

      List<Element> constructorElements = element.elements("constructor-arg");
      ArgumentValues argumentValues = new ArgumentValues();
      for (Element constructorElement : constructorElements) {
        String type = constructorElement.attributeValue("type");
        String name = constructorElement.attributeValue("name");
        String value = constructorElement.attributeValue("value");
        argumentValues.addArgumentValue(new ArgumentValue(type, name, value));
      }
      beanDefinition.setConstructorArgumentValues(argumentValues);


      List<Element> propertyElements = element.elements("property");
      PropertyValues propertyValues = new PropertyValues();
      List<String> refs = new ArrayList<>();
      for (Element propertyElement : propertyElements) {
        String type = propertyElement.attributeValue("type");
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String pRef = propertyElement.attributeValue("ref");
        String pV = "";
        boolean isRef = false;
        if (value != null && !value.equals("")) {
          isRef = false;
          pV = value;
        } else if (pRef != null && !pRef.equals("")) {
          isRef = true;
          refs.add(pRef);
        }
        propertyValues.addPropertyValue(new PropertyValue(type, name, value,isRef));
      }
      beanDefinition.setPropertyValues(propertyValues);

      String[] refArray = refs.toArray(new String[0]);
      beanDefinition.setDependsOn(refArray);

      simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
    }
  }

}
