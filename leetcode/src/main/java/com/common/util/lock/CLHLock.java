package com.common.util.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CHSLock.scala
 * @author: lulong
 * @Date: 2021/10/27
 * @Time: 11:35
 */
public class CLHLock {
    /**
     * 定义一个节点，默认是加锁
     */
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    /**
     * 尾部节点
     */
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        // 新建节点，并与当前线程保存起来
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        // 将新建的节点设置为尾部节点，并返回旧的节点
        CLHNode preNode = UPDATER.getAndSet(this, node);

        if (preNode != null) {
            // 轮询判断前驱节点的锁状态
            while (preNode.isLocked) {

            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }

        node = null;
    }
}
