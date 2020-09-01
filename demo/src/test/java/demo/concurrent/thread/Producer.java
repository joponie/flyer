package demo.concurrent.thread;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class Producer extends Thread {
    MyBlockingQueue queue;

    public Producer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;

        try {
            while (true) {
                String task = String.valueOf(num);
                queue.put(task);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
