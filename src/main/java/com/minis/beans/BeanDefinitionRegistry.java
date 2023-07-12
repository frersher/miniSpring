package com.minis.beans;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public interface BeanDefinitionRegistry {
      void registerBeanDefinition(String name, BeanDefinition bd);
      void removeBeanDefinition(String name);
      BeanDefinition getBeanDefinition(String name);
      boolean containsBeanDefinition(String name);
}
