package jie.flyer.demo.concurrent.reorder;

/**
 * @author kain
 * @since 2020-04-02
 */
public class InstructionReorderDemo {

    private static int a;
    private static int b;
    private static int x;
    private static int y;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 2;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if (x == y) {
                System.out.println(count);
                break;
            }
        }
    }
}
