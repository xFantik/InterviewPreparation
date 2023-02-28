package lesson3;

import java.util.concurrent.locks.Lock;

public class Counter {
    private long count;
    private Lock lock;

    public Counter(Lock lock) {
        this.lock = lock;
    }

    public long getCount() {
        return count;
    }

    public long step() {
        try {
            lock.lock();
            this.count++;
        } finally {
            lock.unlock();
        }
        return count;
    }
}