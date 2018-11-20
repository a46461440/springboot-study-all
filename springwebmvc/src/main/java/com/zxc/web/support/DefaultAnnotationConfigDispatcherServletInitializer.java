package com.zxc.web.support;

import com.zxc.web.config.DispatcherServletConfiguration;
import org.springframework.lang.Nullable;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * SpringFramework依赖于servlet3.0+的实现{@link ServletContainerInitializer#onStartup(Set, ServletContext)},
 * 它的实现类会有一个{@link HandlesTypes}注解,里面是某接口或者抽象类,这样就会servlet容器就会扫描这些接口或者抽象类的实现类
 * 进行筛选
 * servlet3.0以上在创建后会进行一个回调,Spring封装了自己的回调{@link SpringServletContainerInitializer}
 * 它有以下实现接口或者抽象类
 * {@link WebApplicationInitializer},{@link AbstractDispatcherServletInitializer}和
 * {@link AbstractAnnotationConfigDispatcherServletInitializer},如下是使用的注解驱动的方式
 * Spring Web Mvc自动装配 默认实现
 *
 * @NAME DefaultAnnotationConfigDispatcherServletInitializer
 * @DATE 2018/11/19 23:23
 */
public class DefaultAnnotationConfigDispatcherServletInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer{
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() { //web.xml
        return new Class[0];
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() { //DispatcherServlet
        return new Class[]{DispatcherServletConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
