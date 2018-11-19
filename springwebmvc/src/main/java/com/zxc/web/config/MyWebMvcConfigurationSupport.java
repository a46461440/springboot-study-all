package com.zxc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Zhou RunMing
 * @Description 测试是否回合EnableWebMvcConfiguration冲突 (结果：会冲突,这是一种不安全的行为,会覆盖原EnableWebMvc里import的配置类
 * 最好的办法是实现WebMvcConfigurer或它的实现类)
 * @Date 2018-11-19 15:31
 **/
//@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.print("在自定义configuration里执行");
                return true;
            }
        });
    }
}
