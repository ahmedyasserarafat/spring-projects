package com.javarticles.spring;

import org.springframework.context.annotation.Bean;

public class BeanHolder {
    @Bean
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean
    public BeanC beanC() {
        return new BeanC();
    }
}
