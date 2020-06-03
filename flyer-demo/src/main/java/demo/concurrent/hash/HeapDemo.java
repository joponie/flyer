package demo.concurrent.hash;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kain
 * @since 2020-04-10
 */
public class HeapDemo {
    //-XX:+PrintGCDetails -XX:+DisableExplicitGC
    public static void main(String[] args) {
        System.out.println('\u0000');
//        List<ByteBuffer> list = new ArrayList<>();
//        while (true) {
//            ByteBuffer buffer = ByteBuffer.allocateDirect(1 * 1024 * 1024);
//            list.add(buffer);
//            System.gc();
//        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
    }
}
