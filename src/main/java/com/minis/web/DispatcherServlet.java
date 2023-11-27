package com.minis.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/9/1
 **/
public class DispatcherServlet extends HttpServlet {
    private List<String> packageNames = new ArrayList<>();
    private Map<String, MappingValue> mappingValues;
    private String sContextConfigLocation;
    private Map<String, Class<?>> mappingClz = new HashMap<>();
    private Map<String, Object> mappingObjs = new HashMap<>();



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        sContextConfigLocation =  config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;

        try {
            this.getServletContext().getResource(sContextConfigLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        refresh();
    }

    // 读取MappingValues中Bean的定义，加载类，创建实例
    protected void refresh() {
        for (Map.Entry<String, MappingValue> entry : mappingValues.entrySet()) {
            String key = entry.getKey();
            String clzNm = entry.getValue().getClz();
            Object obj = null;
            Class<?> clz = null;

            try {
             clz =  Class.forName(clzNm);
             obj =  clz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mappingClz.put(key, clz);
            mappingObjs.put(key, obj);
        }
    }


    private List<String> scanPackages(List<String> packages) {
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName) {
        List<String> tempControllerNames = new ArrayList<>();
        URL url  =this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if(file.isDirectory()){
                scanPackage(packageName+"."+file.getName());
            }else{
                String controllerName = packageName +"." +file.getName().replace(".class", "");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (mappingValues.get(servletPath) == null){
            return;
        }

        Class<?> clz = this.mappingClz.get(servletPath);
        Object obj = mappingObjs.get(servletPath);
        String methodNm = this.mappingValues.get(servletPath).getMethod();
        Object objRes = null;

        try {
            Method method = clz.getMethod(methodNm);
            objRes  = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().append(objRes.toString());
    }
}