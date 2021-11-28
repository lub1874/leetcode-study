package com.common.util.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName SpinLock.scala
 * @author: lulong
 * @Date: 2021/10/27
 * @Time: 10:08
 */
@Slf4j
public class SpinLock {
    private AtomicReference<Thread> cas;

    public SpinLock(AtomicReference<Thread> cas){
        this.cas = cas;
    }

    /**
     * 获取锁，如果没有获取到锁，则一直循环等待
     */
    public void lock() {
        Thread current = Thread.currentThread();
        while (!cas.compareAndSet(null, current)) {
            log.info("获取到锁，开始处理业务代码");
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
}
