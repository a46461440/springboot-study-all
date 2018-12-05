package com.zxc.spring.reactive.loader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * {@link Future}阻塞数据加载器
 *
 * @author Zhou RunMing
 * @Date 2018-12-5
 */
public class FutureBlockingDataLoader extends DataLoader{

    @Override
    protected void doLoad() {
        ExecutorService service = Executors.newFixedThreadPool(3);
//        this.runCompletely(service.submit(super::loadConfiguration));
//        this.runCompletely(service.submit(super::loadUsers));
//        this.runCompletely(service.submit(super::loadOrders));


        Future future1 = service.submit(super::loadConfiguration);
        Future future2 = service.submit(super::loadUsers);
        Future future3 = service.submit(super::loadOrders);
        this.runCompletely(future1);
        this.runCompletely(future2);
        this.runCompletely(future3);
        service.shutdown();
    }

    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }

}
