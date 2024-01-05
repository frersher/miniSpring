package com.minis;

import com.minis.service.AService;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/11/27
 **/
public class TestMain {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            AService aservice = (AService)context.getBean("aservice");
            aservice.sayHello();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}