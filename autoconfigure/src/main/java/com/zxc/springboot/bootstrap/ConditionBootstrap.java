package com.zxc.springboot.bootstrap;

import com.zxc.springboot.annotation.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class ConditionBootstrap {

    public static void main(String[] args) {
        //RepositoryBootstrap当做一个配置bean
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(ConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        System.out.println(configurableApplicationContext.getBean("littleBean"));
        configurableApplicationContext.close();
    }

    @Bean
    @ConditionalOnSystemProperty(name = "user.name", value = "a")
    public String littleBean() {
        return "hello zxc";
    }

}
