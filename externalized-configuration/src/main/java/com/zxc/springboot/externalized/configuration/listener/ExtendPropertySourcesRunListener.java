package com.zxc.springboot.externalized.configuration.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySource}
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
public class ExtendPropertySourcesRunListener implements SpringApplicationRunListener,Ordered {

    private final SpringApplication application;

    private final String[] args;

    public ExtendPropertySourcesRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {

    }

    /**
     * 以下在SpringApplication运行期执行
     *      /META-INF/default.properties 里面的值为7
     *      /application.properties 里面的值为1
     *      在这里将新增的源放在所有源的首位
     * @param environment
     */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", "0");
        MapPropertySource mapPropertySource = new MapPropertySource("from-my-property-run-listener-environmentPrepared", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", "44");
        MapPropertySource mapPropertySource = new MapPropertySource("from-my-property-run-listener-contextPrepared", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        Map map = new HashMap();
        map.put("user.id", "33");
        MapPropertySource mapPropertySource = new MapPropertySource("from-my-property-run-listener-contextLoaded", map);
        mutablePropertySources.addFirst(mapPropertySource);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        return new EventPublishingRunListener(this.application, this.args).getOrder() - 1;
    }
}
