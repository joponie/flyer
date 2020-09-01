package demo.concurrent.thread;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class MyThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getName() + " My Thread run");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");

        myThread.setPriority(10);
        myThread.setDaemon(true);
        System.out.println(myThread.getState());

        myThread.start();
        System.out.println("main end");
        myThread.join();
        System.out.println("end");
    }
}
