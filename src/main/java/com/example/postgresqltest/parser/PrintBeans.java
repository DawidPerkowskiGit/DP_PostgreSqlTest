package com.example.postgresqltest.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PrintBeans {

    private final ApplicationContext applicationContext;

    @Autowired
    public PrintBeans(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
}
