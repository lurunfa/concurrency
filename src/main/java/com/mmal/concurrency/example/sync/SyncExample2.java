package com.mmal.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kevin
 * @date 2018/5/30
 * @since 0.1.0
 **/
@Slf4j
public class SyncExample2 {
    public static synchronized void test1(int j){
            for (int i = 0; i < 10; i++) {
                log.warn("test1 - i {} - {}",i,j);
            }
    }

    public static void test2(int j){
        synchronized(SyncExample2.class){
            for (int i = 0; i < 10; i++) {
                log.warn("test2 - i {} - j",i,j);
            }
        }
    }

    public static void main(String[] args) {
        SyncExample2 syncExample1 = new SyncExample2();
        SyncExample2 syncExample2 = new SyncExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncExample1.test1(1);
        });
        executorService.execute(() -> {
            syncExample2.test1(2);
        });
    }
}
