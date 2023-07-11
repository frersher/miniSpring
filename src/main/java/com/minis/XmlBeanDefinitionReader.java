package com.minis;

import com.minis.beans.BeanDefinition;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.Resource;
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
      simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
    }
  }

}
