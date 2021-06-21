package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 原子类，保证原子性，内部cas实现
 * 硬件支持cas指令，同时只会有一个线程cas成功，其他都会失败返回
 *
 * @author duchao
 */
public class AtomicTest {

    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                System.out.println(counter.incrementAndGet());
            });
        }
    }
}
