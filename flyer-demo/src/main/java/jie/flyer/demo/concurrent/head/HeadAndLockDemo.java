package jie.flyer.demo.concurrent.head;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kain
 * @since 2020-04-04
 */
public class HeadAndLockDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                syc();
            });
            System.out.println("锁外线程信息==========================");
            System.out.println(ClassLayout.parseInstance(HeadAndLockDemo.class).toPrintable());
            System.out.println("锁外线程信息==========================");
        }
    }

    private static synchronized void syc() {
        System.out.println("线程ID：" + Thread.currentThread().getId());
        System.out.println("锁内线程信息==========================");
        System.out.println(ClassLayout.parseInstance(HeadAndLockDemo.class).toPrintable());
        System.out.println("锁内线程信息==========================");
    }

}
