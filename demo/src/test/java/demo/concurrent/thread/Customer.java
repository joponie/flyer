package demo.concurrent.thread;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class Customer extends Thread {
    MyBlockingQueue queue;

    public Customer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Object take = queue.take();
                System.out.println(take);
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
