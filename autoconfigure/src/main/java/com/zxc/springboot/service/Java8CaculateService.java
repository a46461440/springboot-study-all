package com.zxc.springboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Profile("java8")
@Service
public class Java8CaculateService implements CaculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("java8 lambda循环求和");
        int sum = Stream.of(values).reduce(0, Integer::sum);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Java8CaculateService().sum(1,2,3,4,5));
    }
}
