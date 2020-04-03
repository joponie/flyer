package demo.concurrent.blocking;

/**
 * @author 刘杰鹏
 * @since 2020-04-02
 */
public class InstructionReorderDemo {

    static int a;
    static int b;
    static int x;
    static int y;

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
