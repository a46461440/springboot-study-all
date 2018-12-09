package com.zxc.springboot.externalized.configuration.domain.bo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户 Bo 类
 *
 * @author Zhou RunMing
 * @date 2018/12/8
 */
@Validated //JSR303校验
//@ConfigurationProperties(prefix = "user")
public class User {

    private long id;

    private String name;

    //    @Value("${user.age}")
    private int age;

    //    @Value("${user.desc:hello world}")
    private String desc;

    private City city = new City();

    private static class City {

        private String postCode;

        /**
         * 实现框架会先执行{@link NotEmpty}判断再执行{@link NotNull}判断
         */
        @NotEmpty(message = "城市的名字需要写哦")
        @NotNull(message = "城市的名字不能为空哦")
        private String name;

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "City{" +
                    "postCode='" + postCode + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", city=" + city.toString() +
                '}';
    }
}
