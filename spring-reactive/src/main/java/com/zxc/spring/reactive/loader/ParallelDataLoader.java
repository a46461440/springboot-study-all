package com.zxc.spring.reactive.loader;

import java.util.concurrent.*;

/**
 *
 * 并行执行数据加载
 *
 * @author Zhou RunMing
 * @Date 2018-12-5
 */
public class ParallelDataLoader extends DataLoader {

    @Override
    public void doLoad() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletionService completionService = new ExecutorCompletionService(service);
        completionService.submit(()-> super.loadConfiguration(),null);
        completionService.submit(super::loadUsers,null);
        completionService.submit(super::loadOrders,null);
        int count = 0;
        while (count < 3) {
            if (completionService.poll() != null) {
                count ++;
            }
        }
        service.shutdown();
    }

    public static void main(String[] args) {
        new ParallelDataLoader().load();
    }

}
