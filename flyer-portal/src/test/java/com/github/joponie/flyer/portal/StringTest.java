package com.github.joponie.flyer.portal;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author kain
 * @since 2020-03-26
 */
public class StringTest {
    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, Integer> map = new HashMap<>();
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < 1000; j++) {
                    map.put(random.nextInt(), j);
                }
                latch.countDown();
            }
            );
            thread.start();
        }
        latch.await();
        System.out.println("-------------");
        for (Integer integer : map.keySet()) {
            System.out.println(map.get(integer));
        }
    }

    @Test
    public void dot() {

        System.out.println(Ftest.a);

    }

    @Test
    public void t2() {
        Ftest f = new Ftest();
        Ftest f1 = f;
        System.out.println(f1.equals(f));
    }

    @Test
    public void t3() {
        System.out.println("b");
        Map<String, Integer> ss = new TreeMap<>();
        ss.put(null, 123);
        System.out.println(ss.size());

        Map<String, Integer> map = new HashMap<>(17);
        map.put("B", 123);
    }

}
