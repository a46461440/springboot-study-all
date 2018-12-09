package com.zxc.springboot.externalized.configuration.bootstrap;

import com.zxc.springboot.externalized.configuration.domain.bo.User;
import org.omg.CORBA.Environment;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

/**
 * {@link ConfigurationProperties}引导类
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
@EnableAutoConfiguration
/**
 * 当使用 {@link EnableConfigurationProperties}注解的时候如果里面的{@code Class}是被 {@link ConfigurationProperties}
 * 注解的话则会自动在Spring容器中注入一个该类型的{@code bean}
 */
//@EnableConfigurationProperties(User.class)
@EnableConfigurationProperties
public class ConfigurationPropertiesBootstrap {

    /**
     * 在这里返回的{@code bean}如果是被{@link ConfigurationProperties}标注了的话则会自定注入{@link Environment}
     * 的内容
     * @return
     */
    @Bean
    /**
     * 当{@link ConfigurationProperties}用在方法级别的时候需要返回一个{@code bean},这样才能将{@link Environment}里面的
     * 内容注入到{@code bean}里
     */
    @ConfigurationProperties(prefix = "user")
    /**
     * 在{@link ConditionalOnProperty}里面不能进行松散绑定
     */
    @ConditionalOnProperty(value = "user.city.post-code", havingValue = "0731")
    public User user() {
        return new User();
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        ConfigurableApplicationContext configurableApplicationContext = new
                SpringApplicationBuilder(ConfigurationPropertiesBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        User user = configurableApplicationContext.getBean(User.class);
//        User user = configurableApplicationContext.getBean("user", User.class);
        System.out.println(user.toString());
        configurableApplicationContext.close();
    }

}
