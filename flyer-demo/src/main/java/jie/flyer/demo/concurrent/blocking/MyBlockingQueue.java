package jie.flyer.demo.concurrent.blocking;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kain
 * @since 2020-04-02
 */
public class MyBlockingQueue<E> {

    private Queue<E> queue;

    private int capacity;

    private ReentrantLock lock = new ReentrantLock();

    private Condition full = lock.newCondition();

    private Condition empty = lock.newCondition();

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public void put(E e) throws InterruptedException {
        try {
            lock.lock();
            while (!queue.isEmpty()) {
                full.await();
            }
            queue.add(e);
            String name = Thread.currentThread().getName();
            System.out.println(name + " 生产了 " + e);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                empty.await();
            }
            E e = queue.poll();
            String name = Thread.currentThread().getName();
            System.out.println(name + " 消费了 " + e);
            full.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
