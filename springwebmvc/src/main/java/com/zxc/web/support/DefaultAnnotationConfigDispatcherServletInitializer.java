package com.zxc.web.support;

import com.zxc.web.config.DispatcherServletConfiguration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
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
