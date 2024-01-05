package com.minis;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/11/27
 **/
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
