package demo.concurrent.thread;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class WaitThread extends Thread{

    private volatile boolean fire = false;

    @Override
    public void run() {
        synchronized (this) {
            while (!fire) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("fired!");
        }
    }

    public synchronized void fire() {
        fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(2000L);
        System.out.println("fire!");
        waitThread.fire();
    }
}
