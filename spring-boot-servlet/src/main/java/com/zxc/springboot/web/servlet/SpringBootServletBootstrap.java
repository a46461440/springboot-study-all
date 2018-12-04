package com.zxc.springboot.web.servlet;

import com.zxc.spring.web.servlet.AsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import java.util.EnumSet;

/**
 * spring boot servlet引导类(嵌入式tomcat)
 *
 * @author Zhou RunMing
 * @Date 2018-12-3
 */
@EnableAutoConfiguration
//@ServletComponentScan(basePackages = {"com.zxc.spring.web.servlet"})
public class SpringBootServletBootstrap {

    public static void main(String[] args) {
        new SpringApplication(SpringBootServletBootstrap.class).run(args);
    }


    /**
     * 注册自定义的servlet回调函数
     * @return 返回自定义类似于 {@link ServletContainerInitializer}实现类
     */
    @Bean
    public ServletContextInitializer getServletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic dynamic = servletContext.addFilter("filter", characterEncodingFilter);
            dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }

    /**
     * 以下方式不需要通过ServletComponentScan扫描
     * @return {@link ServletRegistrationBean}可以注册自定义的{@link Servlet}组件
     */
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new AsyncServlet(), "/async-servlet");
        return servletRegistrationBean;
    }

}
