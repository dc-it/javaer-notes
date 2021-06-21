package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号量，控制同时访问并发资源的线程数量
 * 每个线程进来都acquire尝试获取信号量，获取到内部信号量就会-1，直至见到0就会阻塞后来的线程，执行完调用release释放信号量。
 * <p>
 * 信号量设置为1，就变成了互斥锁。
 * 信号量大于1，控制并发数。
 *
 * @author duchao
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(String.format("线程%d%s", finalI + 1, "进来了"));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
