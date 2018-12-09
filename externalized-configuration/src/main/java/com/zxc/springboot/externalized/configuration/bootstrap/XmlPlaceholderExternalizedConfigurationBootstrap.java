package com.zxc.springboot.externalized.configuration.bootstrap;

import com.zxc.springboot.externalized.configuration.domain.bo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * XmlplaceholderExternalizedConfigurationBootstrap 引导类
 *
 * @author Zhou RunMing
 * @date 2018/12/8
 */
@ImportResource("/META-INF/spring/user-context.xml")
@EnableAutoConfiguration
public class XmlPlaceholderExternalizedConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        User user = context.getBean(User.class);
        System.out.println(user.toString());
        System.out.printf("System.getProperties(\"%s\"):%s \n", "user.name", System.getProperty("user.name"));
        context.close();
    }

}
