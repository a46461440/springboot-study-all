package com.zxc.springboot.bootstrap;

import com.zxc.springboot.service.CaculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@ComponentScan(basePackages = {"com.zxc.springboot.repository"})
@SpringBootApplication(scanBasePackages = "com.zxc.springboot.service")
public class CaculateBootstrap {

    public static void main(String[] args) {
        //RepositoryBootstrap当做一个配置bean
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(CaculateBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java7")
                .run(args);
        CaculateService caculateService = configurableApplicationContext.getBean(CaculateService.class);
        System.out.println(caculateService.sum(1,2,3,4,5));
        configurableApplicationContext.close();
    }

}
