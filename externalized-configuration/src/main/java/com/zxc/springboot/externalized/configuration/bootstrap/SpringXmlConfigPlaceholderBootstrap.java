package com.zxc.springboot.externalized.configuration.bootstrap;

import com.zxc.springboot.externalized.configuration.domain.bo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringXmlConfigPlaceholderBootstrap 引导类
 *
 * @author Zhou RunMing
 * @date 2018/12/8
 */
public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {
        String[] locations = {"/META-INF/spring/spring-context.xml", "/META-INF/spring/user-context.xml"};
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(locations);
        User user = classPathXmlApplicationContext.getBean(User.class);
        System.out.println(user.toString());
    }

}
