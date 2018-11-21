package com.zxc.web.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
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
public class InternalResourceViewResolverConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        return internalResourceViewResolver;
    }

}
