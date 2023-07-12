package com.minis.beans;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public interface BeanFactory {

      Object getBean(String name);

//      void registryBean(String beanName,Object obj);

      Boolean containsBean(String name);

      Boolean isSingleton(String name);

      Boolean isPrototype(String name);

      Class<?> getType(String name);
}
