package com.zxc.spring.reactive.loader;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞(串行)数据加载
 *
 * @author Zhou RunMing
 * @Date 2018-12-5
 */
public class DataLoader {

    public final void load() {
        long startTime = System.currentTimeMillis();
        this.doLoad();
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("总耗时:" + costTime + "毫秒");
    }

    protected void doLoad() {
        this.loadConfiguration();
        this.loadUsers();
        this.loadOrders();
    }

    protected void loadConfiguration() {
        this.loadMock("loadConfiguration()", 1);
    }

    protected void loadUsers() {
        this.loadMock("loadUsers()", 2);
    }

    protected void loadOrders() {
        this.loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int second) {
        long startTime = System.currentTimeMillis();
        long millisSecond = TimeUnit.SECONDS.toMillis(second);
        try {
            Thread.sleep(millisSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("[线程:%s]%s耗时:%d毫秒\r\n", Thread.currentThread().getName(), source, costTime);
    }

    public static void main(String[] args) {
        new DataLoader().load();
    }

}
