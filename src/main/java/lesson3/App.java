package lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws InterruptedException {
//        PingPong.start();

        counterTest();
    }

    private static void counterTest() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Counter counter = new Counter(lock);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.step();
                counter.step();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.step();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.step();
            }
        });


        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Счетчик: " + counter.getCount());

    }
}
