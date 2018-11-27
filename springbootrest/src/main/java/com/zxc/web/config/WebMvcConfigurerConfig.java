package com.zxc.web.config;

import com.zxc.web.interceptor.LogInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhou RunMing
 * @Date 2018-11-27 10:41
 */
@Component
public class WebMvcConfigurerConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }
}
