package demo.concurrent.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class CounterThread extends Thread{

    private static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.getAndIncrement();
        }
    }

//    CAS
    public static synchronized void incr() {
//        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i]  = new CounterThread();
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(counter.get());
    }

    public static class Counter {
        private static int count = 0;

        public static synchronized void incr() {
            count++;
        }
    }
}
