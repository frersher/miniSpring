package com.minis.core;

import com.minis.BeanDefinition;
import com.minis.BeanFactory;
import com.minis.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/12/22
 **/
public class SimpleBeanFactory implements BeanFactory {
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = singletons.get(beanName);
        if(null == bean){
            int index = beanNames.indexOf(beanName);
            if (index == -1){
                throw new BeansException("Bean is not defined");
            }
            BeanDefinition definition = beanDefinitions.get(index);

            try {
                Class<?> aClass = Class.forName(definition.getClassName());
                Object instance = aClass.newInstance();
                singletons.put(definition.getClassName(), instance);
                return instance;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}