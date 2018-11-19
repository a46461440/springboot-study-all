package com.zxc.web.filter;

import net.bull.javamelody.MonitoringFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 *
 * Spring集成的Java监控程序
 *
 * @Author Zhou RunMing
 * @Description
 * @Date 2018-11-19 17:21
 **/
@Configuration
public class JavaMelodyConfig {

    @Bean
    public Filter getMelodyConfig() {
        return new MonitoringFilter();
    }

}
