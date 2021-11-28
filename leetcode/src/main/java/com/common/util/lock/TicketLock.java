package com.common.util.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName TicketLock.scala
 * @author: lulong
 * @Date: 2021/10/27
 * @Time: 10:24
 * 公平锁
 */
@Slf4j
public class TicketLock {
    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger();
    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();

    /**
     * 新增一个ThreadLocal，存储每个线程的排队号
     */
    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<>();

    public void lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        log.info("服务号: {}", serviceNum.get());
        log.info("排队号: {}", currentTicketNum);
        // 获取锁的时候，把当前线程的排队号保存
        ticketNumHolder.set(currentTicketNum);
        while(currentTicketNum != serviceNum.get()) {

        }
    }

    public void unlock() {
        // 释放锁的时候，从ThreadLocal获取当前线程的排队号
        Integer currentTicketNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTicketNum, currentTicketNum + 1);
    }
}
