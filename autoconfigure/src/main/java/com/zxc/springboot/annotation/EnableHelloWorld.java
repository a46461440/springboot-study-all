package com.zxc.springboot.annotation;

import com.zxc.noscan.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RespositoryImportSelector.class)
//@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
