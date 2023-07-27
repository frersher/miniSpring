package com.minis.service.impl;

import com.minis.service.AService;
import com.minis.service.BaseService;

public class AServiceImpl implements AService {
    private String name;
    private int level;
    private String property1;
    private String property2;
    private BaseService ref1;

    public BaseService getRef1() {
        return ref1;
    }

    public void setRef1(BaseService bs) {
        this.ref1 = bs;
    }



    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println(this.name + "," + this.level);
    }

    public AServiceImpl() {
    }

    @Override
    public void sayHello() {
        System.out.println(this.property1 + "," + this.property2);
    }
} 