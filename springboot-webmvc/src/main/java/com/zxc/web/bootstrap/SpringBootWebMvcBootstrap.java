package com.zxc.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * Spring Boot Web Mvc 引导类
 *
 * @author Zhou RunMing
 * @Date 2018-11-20 18:23
 */
@SpringBootApplication(scanBasePackages = {"com.zxc.web"})
public class SpringBootWebMvcBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcBootstrap.class, args);
//        new SpringApplicationBuilder(SpringBootWebMvcBootstrap.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
    }

}
