package com.zxc.web.config;

import com.zxc.web.http.converter.properties.PropertiesHttpMessageConverter;
import com.zxc.web.interceptor.LogInterceptor;
import com.zxc.web.method.support.PropertiesHandlerMethodArgumentResolver;
import com.zxc.web.method.support.PropertiesHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-11-27 10:41
 */
@Component
public class WebMvcConfigurerConfig implements WebMvcConfigurer{

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @PostConstruct
    public void init() {
        List<HandlerMethodArgumentResolver> argumentResolvers = this.adapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList();
        newResolvers.add(new PropertiesHandlerMethodArgumentResolver());
        newResolvers.addAll(argumentResolvers);
        this.adapter.setArgumentResolvers(newResolvers);

        List<HandlerMethodReturnValueHandler> returnValueHandlers = this.adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList(returnValueHandlers.size() + 1);
        newReturnValueHandlers.add(new PropertiesHandlerMethodReturnValueHandler());
        newReturnValueHandlers.addAll(returnValueHandlers);
        this.adapter.setReturnValueHandlers(newReturnValueHandlers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesHttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*").allowedMethods("GET");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //添加的HandlerMethodArgumentResolver会被添加到CustomerResolver中,优先级比内置argumentResolver的低
//        if (resolvers.isEmpty())
//            resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
//        else
//            resolvers.set(0, new PropertiesHandlerMethodArgumentResolver());
    }

}
