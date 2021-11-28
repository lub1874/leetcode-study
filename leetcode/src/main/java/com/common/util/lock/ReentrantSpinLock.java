package com.common.util.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ReentrantSpinLock.scala
 * @author: lulong
 * @Date: 2021/10/27
 * @Time: 10:13
 * 可重入自旋锁
 * 引入一个计数器，用来记录获取到锁的线程数
 */
@Slf4j
public class ReentrantSpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int count;

//    public ReentrantSpinLock(AtomicReference<Thread> cas) {
//        this.cas = cas;
//    }

    public void lock() {
        Thread current = Thread.currentThread();
        // 当前线程已经获取到了锁，线程数+1
        if (current == cas.get()) {
            ++count;
            return;
        }

        // 如果没有获取到锁，通过CAS自旋
        while (!cas.compareAndSet(null, current)) {
            log.info("线程: {} 获取到了锁，开始处理业务代码.", current.getName());
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) {
            if (count > 0) {
                --count;
            } else {
//                cas.compareAndSet(null, current);
                cas.set(null);
            }
        }
    }

    public static void main(String[] args) {
        ReentrantSpinLock lock = new ReentrantSpinLock();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                lock.lock();

                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了锁");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
