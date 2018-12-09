package com.zxc.springboot.externalized.configuration.bootstrap;

import com.zxc.springboot.externalized.configuration.domain.bo.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * ValueAnnotationBootstrap 引导类
 *
 * @author Zhou RunMing
 * @date 2018/12/8
 */
@EnableAutoConfiguration
public class ValueAnnotationBootstrap implements BeanFactoryAware, EnvironmentAware {

    /**
     * 以下方式可以将final类型的{@link Environment}的{@code bean}注入进来,但是不符合J2EE的规矩,
     * Spring的注入方式是通过反射,在Java里面是可以注入的,所以才导致这种结果
     */
    @Autowired
    private final Environment _environment = null;

//    private final long id;
//
//    private final String name;
//
//    private final int age;
//
//    private final String desc;
//
//    public ValueAnnotationBootstrap(@Value("${user.id}") long id, @Value("${user.name}") String name,
//                                    @Value("${user.age}") int age, @Value("${user.desc:hello world}") String desc) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.desc = desc;
//    }

    /**
     * @param id
     * @param name
     * @param age  这里为{@link Value}注解的示例 当{@code spel}表达式里面的值不存在的时候可以使用分号后面的值,这里
     *             作为新老版本交替的时候起到的作用非常关键
     * @param desc
     * @return
     */
    @Bean
    public User user(@Value("${user.id}") long id, @Value("${user.name}") String name,
                        @Value("${user.age:${my.user.age:24}}") int age, @Value("${user.desc:hello world}") String desc) {
        User user = new User();
//        user.setId(this.id);
//        user.setName(this.name);
//        user.setAge(this.age);
//        user.setDesc(this.desc);
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setDesc(desc);
        return user;
    }

    /**
     * 当某个方法内的参数在Spring容器中是有这个类型的{@code bean}的时候,Spring会将该{@code bean}
     * 自动注入到这个方法,所以{@code autowired}是可选的,如果写了则语义更加明确,方便别人阅读
     * @param environment
     * @return
     */
    @Bean
    public User user2(@Autowired Environment environment) {
        long id = environment.getRequiredProperty("user.id", Long.class);
        String name = environment.getProperty("user.name");
        Integer age = environment.getProperty("user.age", Integer.class,
                environment.getProperty("my.user.age", Integer.class, 23));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new
                SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        User user = configurableApplicationContext.getBean("user", User.class);
        User user2 = configurableApplicationContext.getBean("user2", User.class);
        System.out.println(user.toString());
        System.out.println(user2.toString());
        configurableApplicationContext.close();
    }

    /**
     * 事实证明{@code autowired}会在以{@code Aware}为后缀的接口方法前执行
     * <p>
     * Spring容器中的environment和{@link BeanFactory}与通过{@link EnvironmentAware}注入的Environment对象是一个
     * 并且在Spring容器中的id为environment
     */
    @Autowired
    private Environment environment;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.environment != beanFactory.getBean(ENVIRONMENT_BEAN_NAME, Environment.class)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (this.environment != environment) {
            throw new IllegalArgumentException();
        }
    }
}
