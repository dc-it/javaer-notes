package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS-ReenTrantLock 同步锁 可重入，可中断，公平/非公平，非阻塞
 *
 * @author duchao
 */
public class ReenTrantLockTest {

    public static void main(String[] args) {

        ReenTrantLockTest test = new ReenTrantLockTest();
        ReentrantLock lock = new ReentrantLock(false);
        ExecutorService service = Executors.newFixedThreadPool(80);
        for (int i = 1; i < 101; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    lock.lock();
                    test.setCount();
                    System.out.println("线程" + finalI + "：" + test.getCount());
                } finally {
                    lock.unlock();
                }
            });
        }
    }

    private int count = 0;

    public void setCount() {
        this.count = this.count + 1;
    }

    public int getCount() {
        return count;
    }
}
