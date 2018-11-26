package com.zxc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * 注入自定义InternalResourceViewResolver改变Jsp优先级
 *
 * @author Zhou RunMing
 * @date 2018/11/21
 */
@Configuration
public class InternalResourceViewResolverConfig implements WebMvcConfigurer{

//    @Bean
//    public ViewResolver myViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setPrefix("/WEB-INF/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        internalResourceViewResolver.setViewClass(JstlView.class);
//        internalResourceViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
//        return internalResourceViewResolver;
//    }

    /**
     * 利用以下方式进行内容协商的策略配置
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //建造者模式构造ContentNegotiationManagerFactoryBean
        configurer.favorParameter(true)
                .favorPathExtension(true);
    }

}
