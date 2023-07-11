package com.minis.beans;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
