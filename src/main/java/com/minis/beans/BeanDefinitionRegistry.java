package com.minis.beans;

import com.minis.BeansException;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public interface BeanDefinitionRegistry {
      void registerBeanDefinition(String name, BeanDefinition bd) throws BeansException;
      void removeBeanDefinition(String name);
      BeanDefinition getBeanDefinition(String name);
      boolean containsBeanDefinition(String name);
}
