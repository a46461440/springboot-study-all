package com.zxc.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Hello World {@link RestController}实现
 *
 * @author Zhou RunMing
 * @Date 2018-12-3
 */
@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private final BlockingQueue<DeferredResult<String>> queue = new LinkedBlockingDeque<>();

    private final Random random = new Random();

    @Scheduled(fixedDelay = 5000)
    public void process() throws InterruptedException {
        DeferredResult<String> result = null;
        do {
            result = queue.take();
            //设置随机超时时间 模拟不同的访问操作(RPC或者DB)
            long timeout = random.nextInt(100);
            Thread.sleep(timeout);
            result.setResult("hello world");
            printlnT("执行计算结果时间:" + timeout + "ms");
        } while (result != null);
    }

    /**
     * 该方式的异步返回是比较常用的,并且比较简单
     * @return
     */
    @GetMapping("callable-hello-world")
    public Callable<String> callableHelloWorld() { //Callable异步执行

        final long startTime = System.currentTimeMillis();
        printlnT("hello world");
        return () -> {
            long costTime = System.currentTimeMillis() - startTime;
            printlnT("执行计算结果时间:" + costTime + "ms");
            return "hello world";
        };
    }

    /**
     * 这种异步返回方式适用于java8及以上,其中supplier是创建java对象的lambda方法引用
     * @return
     */
    @GetMapping("completion-stage")
    public CompletionStage<String> getCompletionStage() {
        final long startTime = System.currentTimeMillis();
        printlnT("hello world");
        return CompletableFuture.supplyAsync(() -> {
            long costTime = System.currentTimeMillis() - startTime;
            printlnT("执行计算结果时间:" + costTime + "ms");
            return "hello world";
        });
    }

    /**
     * 这种异步返回方式可以实现自定义超时与发生错误时的操作,但是使用起来比较繁琐
     * @return {@link DeferredResult}
     */
    @GetMapping("hello-world")
    public DeferredResult<String> helloWorld() { //DeferredResult异步执行
        DeferredResult<String> result = new DeferredResult<>(50L);
//        result.setResult("hello world");
        this.queue.offer(result);
        result.onCompletion(() -> {
            printlnT("执行结束回调");
        });
        result.onTimeout(() -> {
            printlnT("执行超时回调啦!!!");
        });
        printlnT("hello world");
        return result;
    }

    public static void printlnT(Object o) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + threadName + "]:" + o);
    }

}
