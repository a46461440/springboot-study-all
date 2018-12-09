package com.zxc.springboot.externalized.configuration.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySource}{@link EnvironmentPostProcessor}实现
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
public class ExtendPropertySourcesEnvironmentPostProcessor implements EnvironmentPostProcessor,Ordered{
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", 66);
        MapPropertySource mapPropertySource = new MapPropertySource("from-ExtendPropertySourcesEnvironmentPostProcessor", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }
}
