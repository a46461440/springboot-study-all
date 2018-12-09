package com.zxc.springboot.externalized.configuration.test;

import org.apache.logging.log4j.util.PropertySource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 外部化配置属性源{@link PropertySource}顺序测试用例
 *
 * @author Zhou RunMing
 * @date 2018/12/9
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "user.id=9",
        locations = {"classpath:META-INF/default.properties"}
)
@SpringBootTest(classes = {PropertySourceOrderTest.class, PropertySourceOrderTest.MyConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "user.id=8"
)
public class PropertySourceOrderTest {

    @Configuration
    @org.springframework.context.annotation.PropertySource(name = "test-property-source", value = "classpath:META-INF/test.properties")
    public static class MyConfig{

    }

    @Value("${user.id}")
    private long id;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testEnvironment() {
        Assert.assertSame(this.environment, this.context.getEnvironment());
    }

    @Test
    public void testUserId() {
//        Assert.assertEquals(9L, this.id);
        System.out.println(this.id);
    }

    @Test
    public void printPropertiesResources() {
        this.environment.getPropertySources().forEach(resource -> {
            System.out.printf("PropertiesSource[名称:%s]:%s \n", resource.getName(), resource);
        });
    }

}
