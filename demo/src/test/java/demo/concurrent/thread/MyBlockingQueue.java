package demo.concurrent.thread;

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

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public synchronized void put(E e) throws InterruptedException {
            while (queue.size() == capacity) {
                wait();
            }
            queue.add(e);
            String name = Thread.currentThread().getName();
            System.out.println(name + " 生产了 " + e);
            notifyAll();
    }

    public synchronized E take() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            E e = queue.poll();
            String name = Thread.currentThread().getName();
            System.out.println(name + " 消费了 " + e);
            notifyAll();
            return e;
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Customer(queue).start();

        System.out.println(1 / 0);
    }
}
