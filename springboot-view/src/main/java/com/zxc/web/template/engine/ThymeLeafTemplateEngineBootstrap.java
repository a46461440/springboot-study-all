package com.zxc.web.template.engine;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * ThymeLeaf 模板引擎类
 *
 * @author Zhou RunMing
 * @date 2018/11/21
 */
@Slf4j
public class ThymeLeafTemplateEngineBootstrap {

    public static void main(String[] args) throws IOException {
        //创建引擎
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        //创建上下文
        Context context = new Context();
        context.setVariable("message", "hello world");
        //如下为spring读取文件内容,输出到输出流然后再转化为String当做content后放入thymeleaf引擎渲染的过程
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:templates/thymeleaf/hello-world.html");
        File file = resource.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(fileInputStream, byteArrayOutputStream);
        String content = byteArrayOutputStream.toString("utf-8");
        fileInputStream.close();
        //用以下方式是spring读取文件之后的结果
//        String content = "<p th:text=\"${message}\">!!!</p>";
        String result = springTemplateEngine.process(content, context);
//        log.info(result);
        System.out.println(result);
    }

}
