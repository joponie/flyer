package demo.concurrent.blocking;

/**
 * @author kain
 * @since 2020-04-02
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(100);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        producer.start();
        consumer.start();

        Thread.sleep(2000);
        producer.interrupt();

        Thread.sleep(2000);
        consumer.interrupt();
    }

    static class Producer extends Thread {

        MyBlockingQueue<Integer> queue;

        public Producer(MyBlockingQueue<Integer> queue) {
            setName("生产者");
            this.queue = queue;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                try {
                    i++;
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    static class Consumer extends Thread {

        MyBlockingQueue<Integer> queue;

        public Consumer(MyBlockingQueue<Integer> queue) {
            setName("消费者");
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

}
