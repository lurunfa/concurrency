package com.mmal.concurrency.example.atomic;

import com.mmal.concurrency.annoatons.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @author kevin
 * @date 2018/5/30
 * @since 0.1.0
 **/
@Slf4j
@ThreadSafe
public class AtomicExample1 {

    private static AtomicIntegerFieldUpdater<AtomicExample1> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample1.class,"count");

    @Getter
    private volatile int count = 100;

    private static AtomicExample1 example1 = new AtomicExample1();

    public static void main(String[] args) {
        if (atomicIntegerFieldUpdater.compareAndSet(example1,100,120)){
            log.warn("update success 1 {}",example1.getCount());
        }
        if (atomicIntegerFieldUpdater.compareAndSet(example1,100,120)){
            log.warn("update success 2 {}",example1.getCount());
        }else {
            log.warn("update fail {}",example1.getCount());
        }
    }

}
