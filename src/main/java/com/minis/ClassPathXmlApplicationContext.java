package com.minis;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class ClassPathXmlApplicationContext implements BeanFactory{

    BeanFactory beanFactory;

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
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }
}
