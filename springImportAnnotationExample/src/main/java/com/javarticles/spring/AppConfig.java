package com.javarticles.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BeanA beanA() {
        return new BeanA();
    }
    
    @Bean
    public InnerBean innerBean() {
        return new InnerBean();
    }

    static class InnerBean {       
    }
}
