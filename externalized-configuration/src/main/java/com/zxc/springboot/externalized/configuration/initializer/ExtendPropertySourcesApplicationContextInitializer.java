package com.zxc.springboot.externalized.configuration.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySource}{@link ApplicationContextInitializer}实现
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
public class ExtendPropertySourcesApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", "55");
        MapPropertySource mapPropertySource = new MapPropertySource("from-ExtendPropertySourcesApplicationContextInitializer", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }
}
