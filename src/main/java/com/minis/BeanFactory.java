package com.minis;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public interface BeanFactory {
      Object getBean(String name);

      void registerBeanDefinition(BeanDefinition beanDefinition);
}
