package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

  private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();


  @Override
  public Object getBean(String name) {
    Object singleton = this.singletonObjects.get(name);
    if (null == singleton){
       BeanDefinition beanDefinition = beanDefinitions.get(name);
       singleton = createBean(beanDefinition);
      this.registerSingleton(name,singleton);
    }
    return singleton;
  }

  private Object createBean(BeanDefinition beanDefinition) {
    try {
      return Class.forName(beanDefinition.getClassName()).newInstance();
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }



  @Override
  public void registryBean(String beanName, Object obj) {
     this.registerSingleton(beanName, obj);
  }

  @Override
  public Boolean containsBean(String name) {
    return this.containsSingleton(name);
  }

  public void registerBeanDefinition(String name, BeanDefinition bd) {
    this.beanDefinitions.put(name, bd);
  }
}
