package jie.flyer.demo.concurrent.thread;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("myRunnable run");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        System.out.println("main end");
    }
}
