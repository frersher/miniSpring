package com.minis;

import org.dom4j.Element;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class XmlBeanDefinitionReader {

  BeanFactory factory;

  public XmlBeanDefinitionReader(BeanFactory factory) {
    this.factory = factory;
  }

  public void loadBeanDefinitions(Resource resource) throws BeansException {
    while (resource.hasNext()) {
      Element element = (Element) resource.next();
      String id = element.attributeValue("id");
      String className = element.attributeValue("class");
      BeanDefinition beanDefinition = new BeanDefinition(id, className);
      beanDefinition.setClassName(className);
      factory.registerBeanDefinition(beanDefinition);
    }
  }

}
