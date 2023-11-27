package com.minis.web;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/9/1
 **/
public class MappingValue {
    String uri;
    String clz;
    String method;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MappingValue(String uri, String clz, String method) {
        this.uri = uri;
        this.clz = clz;
        this.method = method;
    }
}