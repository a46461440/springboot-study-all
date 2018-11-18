package com.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationEventBootstrap {

    public static void main(String[] args) {
        //创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(event ->{
            ApplicationContext applicationContext = (ApplicationContext) event.getSource();
            System.out.println("监听到事件" + event);
        });
        //启动上下文
        context.refresh();
        context.start();
        context.publishEvent("HelloWorld");
        //关闭上下文
        context.close();
    }

}
