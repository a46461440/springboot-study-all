package com.zxc.springboot;

import com.zxc.springboot.annotation.EnableHelloWorld;
import com.zxc.springboot.service.CaculateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableHelloWorld
@EnableAutoConfiguration
@EnableAsync
@ServletComponentScan({"com.zxc.springboot.web.servlet", "com.zxc.springboot.filter", "com.zxc.springboot.listener"})
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);
		System.out.println(configurableApplicationContext.getBean("helloWorld"));
	}
}
