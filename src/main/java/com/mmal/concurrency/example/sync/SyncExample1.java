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
public class SyncExample1 {
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.warn("test1 - i {} - {}",i,j);
            }
        }
    }

    public synchronized void test2(int j){
            for (int i = 0; i < 10; i++) {
                log.warn("test2 - i {} - j",i,j);
            }
    }

    public static void main(String[] args) {
        SyncExample1 syncExample1 = new SyncExample1();
        SyncExample1 syncExample2 = new SyncExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncExample1.test1(1);
        });
        executorService.execute(() -> {
            syncExample2.test1(2);
        });
    }
}
