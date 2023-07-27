package com.minis.context;

import com.minis.BeansException;
import com.minis.XmlBeanDefinitionReader;
import com.minis.beans.BeanFactory;
import com.minis.beans.SimpleBeanFactory;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class ClassPathXmlApplicationContext implements BeanFactory ,ApplicationEventPublisher{

    SimpleBeanFactory beanFactory;




    public ClassPathXmlApplicationContext(String path) throws BeansException {
      this(path, true);
    }


    public ClassPathXmlApplicationContext(String path, boolean isRefresh) throws BeansException {
        Resource res = new ClassPathXmlResource(path);
        SimpleBeanFactory bf = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);
        this.beanFactory = bf;
        if (isRefresh) {
            beanFactory.refresh();
        }
    }


    @Override
    public Object getBean(String name) throws BeansException {
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
