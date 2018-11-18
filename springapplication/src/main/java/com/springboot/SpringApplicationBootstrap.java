package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

public class SpringApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        Set<String> sources = new HashSet<String>();
        sources.add(ApplicationConfiguration.class.getName());
//        sources.add(SpringApplicationBootstrap.class.getName());
        application.setSources(sources);
//        application.setWebApplicationType(WebApplicationType.REACTIVE); //WebFlux容器
//        application.setWebApplicationType(WebApplicationType.SERVLET); //SpringMVC容器
        application.setWebApplicationType(WebApplicationType.NONE); //普通应用 (不开启8080端口)
        application.addListeners(event -> {
            System.out.println("监听到事件" + event);
        });
        ApplicationContext context = application.run(args);
        System.out.println(context.getBean(ApplicationConfiguration.class));
    }

    @SpringBootApplication
    public static class ApplicationConfiguration {
    }

}
