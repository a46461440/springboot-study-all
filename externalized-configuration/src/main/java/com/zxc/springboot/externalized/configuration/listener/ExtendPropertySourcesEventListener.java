package com.zxc.springboot.externalized.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySource} {@link ApplicationEvent}实现,监听{@link ApplicationEnvironmentPreparedEvent}实践
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
public class ExtendPropertySourcesEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        ConfigurableEnvironment environment = applicationEnvironmentPreparedEvent.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", 77);
        MapPropertySource mapPropertySource = new MapPropertySource("from-ExtendPropertySourcesEventListener", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }
}
