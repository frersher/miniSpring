package com.minis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class SimpleBeanFactory implements BeanFactory {
     private List<BeanDefinition> beanDefinitions = new ArrayList<>();
     private Map<String,Object> singletons = new HashMap<>();
     private List<String> beanNames = new ArrayList<>();


  @Override
  public Object getBean(String name) {
    if (singletons.containsKey(name)) {
      return singletons.get(name);
    }
    for (BeanDefinition beanDefinition : beanDefinitions) {
      if (beanDefinition.getId().equals(name)) {
        try {
          Class<?> clazz = Class.forName(beanDefinition.getClassName());
          Object instance = clazz.newInstance();
          singletons.put(name, instance);
          return instance;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  @Override
  public void registerBeanDefinition(BeanDefinition beanDefinition) {
    beanDefinitions.add(beanDefinition);
    beanNames.add(beanDefinition.getId());
  }

}
