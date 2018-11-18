package com.zxc.springboot.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String helloWorld = String.valueOf(configurableApplicationContext.getBean("helloWorld2"));
        System.out.println(helloWorld);
        helloWorld = String.valueOf(configurableApplicationContext.getBean("helloWorld"));
        System.out.println(helloWorld);
        configurableApplicationContext.close();
    }

}
