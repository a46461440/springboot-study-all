package com.zxc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试是否会和nableWebMvc冲突 (结果：会冲突,这是一种不安全的行为,会覆盖原EnableWebMvc里import的配置类
 * 最好的办法是实现{@link WebMvcConfigurer}或它的实现类)
 * @see EnableWebMvc (EnableWebMvc是个注解 里面注入了{@link WebMvcConfigurationSupport}配置类)
 * @author Zhou RunMing
 * @date 2018-11-19
 **/
//@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    /**
     * 为SpringMvc添加{@link HandlerInterceptor}(拦截器)
     * @param registry 拦截器存储容器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("在自定义configuration里执行");
                return true;
            }
        });
    }
}
