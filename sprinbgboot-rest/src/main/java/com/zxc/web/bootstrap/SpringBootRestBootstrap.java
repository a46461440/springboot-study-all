package com.zxc.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Rest引导类
 *
 * @author Zhou RunMing
 * @date 2018/11/26
 */
@SpringBootApplication(scanBasePackages = "com.zxc.web")
public class SpringBootRestBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestBootstrap.class, args);
    }

}
