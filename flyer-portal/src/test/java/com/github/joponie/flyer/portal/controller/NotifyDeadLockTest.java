package com.github.joponie.flyer.portal.controller;

/**
 * @author kain
 * @since 2020-02-07
 */
public class NotifyDeadLockTest {

    static class OutTurn {
        private boolean isSub = true;
        private int count = 0;

        public synchronized void sub() {
            try {
                while (!isSub ) {
                    this.wait();
                }
                System. out.println("sub ---- " + count);
                isSub = false ;
                NotifyDeadLockTest.class.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;

        }

        public synchronized void main() {
            try {
                while (isSub ) {
                    this.wait();
                }
                System. out.println("main (((((((((((( " + count);
                isSub = true ;
                this.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void main(String[] args) {
        final OutTurn ot = new OutTurn();

        for (int j = 0; j < 100; j++) {

            new Thread(new Runnable() {

                public void run() {
                     try {
                     Thread.sleep(10);
                     } catch (InterruptedException e) {
                     e.printStackTrace();
                     }
                    for (int i = 0; i < 5; i++) {
                        ot.sub();
                    }
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                     try {
                     Thread.sleep(10);
                     } catch (InterruptedException e) {
                     e.printStackTrace();
                     }
                    for (int i = 0; i < 5; i++) {
                        ot.main();
                    }
                }
            }).start();
        }
    }
}
