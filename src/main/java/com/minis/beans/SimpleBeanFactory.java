package com.minis.beans;

import com.minis.BeansException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory,BeanDefinitionRegistry {

  private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();
  private List<String> beanDefinitionNames = new ArrayList<>();
  private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);


  public SimpleBeanFactory() {
  }

  public void refresh() {
    for (String beanName : beanDefinitionNames) {
      try {
        getBean(beanName);
      } catch (BeansException e) {
        e.printStackTrace();
      }
    }
  }
  @Override
  public Object getBean(String name) throws BeansException {
    Object singleton = this.getSingleton(name);
    if (singleton == null){
      singleton = this.earlySingletonObjects.get(name);
      if (singleton == null){
        BeanDefinition bd = beanDefinitions.get(name);
        singleton = createBean(bd);
        this.registerSingleton(name, singleton);
      }

    }
    if (singleton == null){
      throw new BeansException("bean is null.");
    }
    return singleton;
  }

  private Object createBean(BeanDefinition bd) {
    Class<?> clz = null;
    // 创建毛坯bean实例
    Object obj = doCreateBean(bd);
    // 存放到实例缓存
    this.earlySingletonObjects.put(bd.getId(), obj);

    try {
      clz = Class.forName(bd.getClassName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // 处理属性
    handleProperties(bd,clz,obj);
   return obj;
  }


  private Object doCreateBean(BeanDefinition bd){
    Class<?> clz = null;
    Object obj = null;
    Constructor<?> con;

    try {
      clz = Class.forName(bd.getClassName());

      //handle constructor
      ArgumentValues argumentValues = bd.getConstructorArgumentValues();
      if (!argumentValues.isEmpty()) {
        Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
        Object[] paramValues = new Object[argumentValues.getArgumentCount()];
        for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
          ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
          if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(
              argumentValue.getType())) {
            paramTypes[i] = String.class;
            paramValues[i] = argumentValue.getValue();
          } else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(
              argumentValue.getType())) {
            paramTypes[i] = Integer.class;
            paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
          } else if ("int".equals(argumentValue.getType())) {
            paramTypes[i] = int.class;
            paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
          } else {
            paramTypes[i] = String.class;
            paramValues[i] = argumentValue.getValue();
          }
        }
        try {
          con = clz.getConstructor(paramTypes);
          obj = con.newInstance(paramValues);
        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
          e.printStackTrace();
        }
      } else {
        obj = clz.newInstance();
      }

    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
      e.printStackTrace();
    }

    //handle properties
    handleProperties(bd, clz,obj);
    return obj;

  }



  private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj){
    // 处理属性
    PropertyValues propertyValues = bd.getPropertyValues();

    if(!propertyValues.isEmpty()){
        for (int i=0;i<propertyValues.size();i++){

          PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
          String pName = propertyValue.getName();
          String pType = propertyValue.getType();
          Object pValue = propertyValue.getValue();
          boolean ref = propertyValue.isRef();

          Class<?>[] paramTypes = new Class<?>[1];
          Object[] paramValues = new Object[1];

          if (!ref){
            if ("String".equals(pType) || "java.lang.String".equals(pType)) {
              paramTypes[0] = String.class;
            } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
              paramTypes[0] = Integer.class;
            } else if ("int".equals(pType)) {
              paramTypes[0] = int.class;
            } else {
              paramTypes[0] = String.class;
            }

            paramValues[0] = pValue;
          }else {
            try {
              paramTypes[0] = Class.forName(pType);
            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            }
            try {
              paramValues[0] = getBean((String) pValue);
            } catch (BeansException e) {
              e.printStackTrace();
            }
          }



          String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);

          Method method = null;
          try {
            method = clz.getMethod(methodName, paramTypes);
          } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
          }
          try {
            method.invoke(obj, paramValues);
          } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            e.printStackTrace();
          }
        }
    }

  }

  public void registryBean(String beanName, Object obj) {
     this.registerSingleton(beanName, obj);
  }

  @Override
  public Boolean containsBean(String name) {
    return this.containsSingleton(name);
  }

  @Override
  public Boolean isSingleton(String name) {
    return this.beanDefinitions.get(name).isSingleton();
  }

  @Override
  public Boolean isPrototype(String name) {
    return this.beanDefinitions.get(name).isPrototype();
  }

  @Override
  public Class<?> getType(String name) {
    return beanDefinitions.get(name).getClass();
  }

  @Override
  public void registerBeanDefinition(String name, BeanDefinition bd) throws BeansException {
    this.beanDefinitions.put(name, bd);
    this.beanDefinitionNames.add(name);
    if(!bd.isLazyInit()){
      getBean(name);
    }
  }

  @Override
  public void removeBeanDefinition(String name) {
    this.beanDefinitions.remove(name);
    this.beanDefinitionNames.remove(name);
  }

  @Override
  public BeanDefinition getBeanDefinition(String name) {
    return beanDefinitions.get(name);
  }

  @Override
  public boolean containsBeanDefinition(String name) {
    return beanDefinitions.containsKey(name);
  }
}
