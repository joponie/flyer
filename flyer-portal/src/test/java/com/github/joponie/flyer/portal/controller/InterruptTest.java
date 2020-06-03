package com.github.joponie.flyer.portal.controller;

import org.junit.Test;

/**
 * @author kain
 * @since 2020-01-17
 */
public class InterruptTest {

    @Test
    public void test1() throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("continue");
            }
            System.out.println("end");
        });
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println("all done");
        System.out.println(thread.isInterrupted());
    }

    @Test
    public void t2() throws InterruptedException {
        HaThread haThread = new HaThread();
        haThread.start();
        haThread.join();
        System.out.println("end");
    }

    public static class HaThread extends Thread {
        @Override
        public void run() {
            try {
                interrupt();
                System.out.println(isInterrupted());
                sleep(1000L);
            } catch (InterruptedException e) {

                e.printStackTrace();
                System.out.println(isInterrupted());
                System.out.println(getName() + "发生中断");
            }
            System.out.println(isInterrupted());
        }
    }
}
