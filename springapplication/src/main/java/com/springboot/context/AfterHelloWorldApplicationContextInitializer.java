package com.springboot.context;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 *
 * {@link ApplicationContextInitializer}会在
 * {@link SpringApplication#refresh(ApplicationContext)}之前调用
 *
 * @author Zhou RunMing
 * @date 2018-11-20
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AfterHelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext>
        implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("After configurableApplicationContext.id = " + configurableApplicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
