package com.zxc.spring.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * 链式数据加载器
 *
 * @author Zhou RunMing
 * @Date 2018-12-5
 */
public class ChainDataLoader extends DataLoader {

    @Override
    protected void doLoad() {
        CompletableFuture
                .runAsync(super::loadConfiguration)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                .whenComplete((result, throwable) -> {
                    System.out.println("[线程:" + Thread.currentThread().getName() + "]加载完成");
                }).join();
    }

    public static void main(String[] args) {
        new ChainDataLoader().load();
    }
}
