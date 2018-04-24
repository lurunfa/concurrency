package com.mmal.concurrency;

import com.mmal.concurrency.annoatons.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发测试 代码版本
 *
 * @author kevin
 * @date 2018/4/24
 * @since 1.0
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发的数目
     */
    public static int threadTotal = 200;

    /**
     * 计数器
     */
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        count++;
    }
}
