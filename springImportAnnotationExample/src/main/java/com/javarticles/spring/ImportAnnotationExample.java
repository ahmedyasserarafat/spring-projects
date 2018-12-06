package com.javarticles.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig.class, BeanHolder.class})
public class ImportAnnotationExample {
    @Autowired
    private AppConfig appConfig;
    
    @Autowired
    private BeanB beanB;
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ImportAnnotationExample.class);
        
        printBean(context, "com.javarticles.spring.AppConfig");
        printBean(context, "beanA");
        printBean(context, "beanB");
        printBean(context, "beanC");
        printBean(context, "innerBean");
        
        ImportAnnotationExample importAnnotationExample = (ImportAnnotationExample) 
                context.getBean("importAnnotationExample");
        System.out.println("AppConfig member: " + importAnnotationExample.getAppConfig());
        System.out.println("BeanB member: " + importAnnotationExample.getBeanB());
        
    }

    public static void printBean(ApplicationContext context, String beanId) {
        System.out.println(beanId + ": " + context.getBean(beanId));
    }
    
    public AppConfig getAppConfig() {
        return appConfig;
    }

    public BeanB getBeanB() {
        return beanB;
    }       
}
