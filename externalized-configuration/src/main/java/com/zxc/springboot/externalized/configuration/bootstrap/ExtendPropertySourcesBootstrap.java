package com.zxc.springboot.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

/**
 * 扩展{@link PropertySource} 的引导类
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
@EnableAutoConfiguration
@org.springframework.context.annotation.PropertySource(name = "from-classpath:META-INF/default.properties", value = {"classpath:META-INF/default.properties"})
public class ExtendPropertySourcesBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new
                SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                .web(WebApplicationType.NONE)
                .properties("user.id=99")
                .run(of("user.id=88"));
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        long id = environment.getProperty("user.id", Long.class);
        System.out.printf("用户id:%d \n", id);
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertiesSource[名称:%s]:%s \n", propertySource.getName(), propertySource);
        });
        configurableApplicationContext.close();
    }

    private static <T> T[] of(T... args) {
        return args;
    }

}
