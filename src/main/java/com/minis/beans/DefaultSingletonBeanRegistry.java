package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    protected List<String> singletonBeanNames = new ArrayList<>(64);
    protected final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
       synchronized (singletonObjects){
           Object oldObj = singletonObjects.get(beanName);
              if (oldObj != null){
                throw new IllegalStateException("Could not register object [" + singletonObject +
                          "] under bean name '" + beanName + "': there is already object [" + oldObj + "] bound");
              }
           singletonObjects.put(beanName, singletonObject);
           singletonBeanNames.add(beanName);
           System.out.println(" bean registerded............. " + beanName);
       }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return singletonBeanNames.toArray(new String[singletonBeanNames.size()]);
    }

    protected void removeSingleton(String beanName) {
        synchronized (singletonObjects) {
            singletonObjects.remove(beanName);
            singletonBeanNames.remove(beanName);
        }
    }

}
