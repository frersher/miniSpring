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
public class ClassPathXmlApplicationContext implements BeanFactory {

    SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String path) throws BeansException {
        beanFactory = new SimpleBeanFactory();
        ClassPathXmlResource resource = new ClassPathXmlResource(path);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }


    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public void registryBean(String beanName, Object obj) {
        beanFactory.registryBean(beanName, obj);
    }

    @Override
    public Boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }


}
