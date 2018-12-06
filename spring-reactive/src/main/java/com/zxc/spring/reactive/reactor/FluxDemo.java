package com.zxc.spring.reactive.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Flux实例
 *
 * @author Zhou RunMing
 * @date 2018/12/6
 */
public class FluxDemo {

    public static void main(String[] args) throws InterruptedException {
        Flux.just("A", "B", "C") //发布A->B->C
                .map(value -> "+" + value)
                .publishOn(Schedulers.elastic())
                .subscribe(FluxDemo::println, //数据消费 = onNext(T)
                        FluxDemo::println,    //数据异常 = onError(Throwable)
                        () -> println("完成操作!!!"), //完成回调 = onComplete()
                        subscription -> { //背压操作 = onSubscribe(Subscription)
//                            subscription.request(2); //请求处理的元素数量
                            subscription.cancel(); //取消上游数据传递到下游
                        }
                );
        Flux.just("A", "B", "C") //发布A->B->C
                .map(value -> "+" + value)
                .publishOn(Schedulers.single())
                .subscribe(new Subscriber<String>() {

                    private Subscription subscription;

                    private int count;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        if (count == 2) {
//                            throw new RuntimeException("故意抛出异常");
                        }
                        count++;
                        this.subscription.request(1);
                        println(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        println(throwable);
                    }

                    @Override
                    public void onComplete() {
                        println("操作完成!!!");
                    }
                });
        Thread.sleep(1000);
    }

    private static void println(Object o) {
        String threadName = Thread.currentThread().getName();
        System.out.println("线程[" + threadName + "]" + o);
    }

}
