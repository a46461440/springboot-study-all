package com.zxc.springboot.configuration;

import com.zxc.springboot.annotation.ConditionalOnSystemProperty;
import com.zxc.springboot.annotation.EnableHelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHelloWorld
@ConditionalOnSystemProperty(name = "user.name", value = "a")
public class HelloWorldAutoConfiguration {

    @Bean
    public String helloWorld2() {
        return "hello world 2";
    }

}
