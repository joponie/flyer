package demo.concurrent.thread;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class InterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();

        Thread.sleep(1000L);
        interruptedThread.interrupt();

    }

    public static class InterruptedThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                System.out.println(i++);
            }
            System.out.println("Interrupted");
        }
    }

}
