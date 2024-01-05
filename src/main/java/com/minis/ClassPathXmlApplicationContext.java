package com.minis;

import com.minis.core.ClassPathXmlResource;
import com.minis.core.SimpleBeanFactory;
import com.minis.core.XmlBeanDefinitionReader;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/11/27
 **/
public class ClassPathXmlApplicationContext implements BeanFactory{
    private BeanFactory beanFactory = new SimpleBeanFactory();

    public ClassPathXmlApplicationContext(String xmlPath) throws BeansException {
        Resource classPathXmlResource = new ClassPathXmlResource(xmlPath);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(classPathXmlResource);
    }


    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
         beanFactory.registerBeanDefinition(beanDefinition);
    }

    @Override
    public Object getBean(String beanId) throws BeansException {
        return beanFactory.getBean(beanId);
    }

}