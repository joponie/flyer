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
            t1.start();
            t1.join();
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t2.start();
            t2.join();
            if (x == 0 && y == 0) {
                System.out.println(count);
                break;
            }
        }
    }
}
