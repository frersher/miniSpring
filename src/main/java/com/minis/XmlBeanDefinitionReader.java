package com.minis;

import com.minis.beans.ArgumentValue;
import com.minis.beans.ArgumentValues;
import com.minis.beans.BeanDefinition;
import com.minis.beans.PropertyValue;
import com.minis.beans.PropertyValues;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.Resource;
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

      List<Element> propertyElements = element.elements("property");
      PropertyValues propertyValues = new PropertyValues();

      for (Element propertyElement : propertyElements) {
        String type = propertyElement.attributeValue("type");
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        propertyValues.addPropertyValue(new PropertyValue(type, name, value));
      }
      beanDefinition.setPropertyValues(propertyValues);

      List<Element> constructorElements = element.elements("constructor-arg");
      ArgumentValues argumentValues = new ArgumentValues();
      for (Element constructorElement : constructorElements) {
        String type = constructorElement.attributeValue("type");
        String name = constructorElement.attributeValue("name");
        String value = constructorElement.attributeValue("value");
        argumentValues.addArgumentValue(new ArgumentValue(type, name, value));
      }
       beanDefinition.setConstructorArgumentValues(argumentValues);

      simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
    }
  }

}
