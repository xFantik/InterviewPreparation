package lesson3;

public class PingPong {
    private static final Object mon = new Object();

    private static boolean step = false;


    public static void start() {
        new Thread(() -> {
            while (true) {
                synchronized (mon) {
                    try {
                        while (step) {
                            mon.wait();
                        }
                        System.out.println("ping");
                        step = true;
                        mon.notifyAll();
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (mon) {
                    try {
                        while (!step) {
                            mon.wait();
                        }
                        System.out.println(" - pong");
                        step = false;
                        mon.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


}
