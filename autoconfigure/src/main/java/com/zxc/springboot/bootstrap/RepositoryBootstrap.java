package com.zxc.springboot.bootstrap;

import com.zxc.springboot.repository.FirstRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.zxc.springboot.repository"})
public class RepositoryBootstrap {

    public static void main(String[] args) {
        //RepositoryBootstrap当做一个配置bean
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(RepositoryBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        FirstRepository firstRepository = configurableApplicationContext.getBean("firstRepository", FirstRepository.class);
        firstRepository.say();
        System.out.println(firstRepository.toString());
        configurableApplicationContext.close();
    }

}
