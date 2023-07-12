package com.minis.context;

import com.minis.BeansException;
import com.minis.XmlBeanDefinitionReader;
import com.minis.beans.BeanFactory;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.ClassPathXmlResource;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class ClassPathXmlApplicationContext implements BeanFactory ,ApplicationEventPublisher{

    SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String path) throws BeansException {
        beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        ClassPathXmlResource resource = new ClassPathXmlResource(path);
        reader.loadBeanDefinitions(resource);
    }


    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }


    @Override
    public Boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    @Override
    public Boolean isSingleton(String name) {
        return null;
    }

    @Override
    public Boolean isPrototype(String name) {
        return null;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }


}
