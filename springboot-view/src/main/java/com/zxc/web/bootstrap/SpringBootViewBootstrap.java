package com.zxc.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 视图引导类
 *
 * @author Zhou RunMing
 * @date 2018/11/21
 */
@SpringBootApplication(scanBasePackages = "com.zxc.web")
public class SpringBootViewBootstrap {

    public static void main(String[] args) {
        new SpringApplication(SpringBootViewBootstrap.class)
                .run(args);
    }

}
