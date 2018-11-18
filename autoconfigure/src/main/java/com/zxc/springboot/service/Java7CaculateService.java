package com.zxc.springboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("java7")
@Service
public class Java7CaculateService implements CaculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("java7 for循环求和");
        int sum = 0;
        for (int i = 0; i < values.length; i++)
            sum += values[i];
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Java7CaculateService().sum(1,2,3,4,5));
    }
}
