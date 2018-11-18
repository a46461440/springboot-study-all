package com.zxc.springboot.repository;

import com.zxc.springboot.annotation.FirstLevelRepository;

@FirstLevelRepository("firstRepository")
public class FirstRepository {

    public void say() {
        System.out.println("hello 2018");
    }

}
