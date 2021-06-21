package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 同步计算器。
 * 内部countDown-1操作，直到为0，await阻塞的线程才会继续执行
 * 执行countDown的线程执行后会继续执行
 *
 * @author duchao
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            service.execute(()->{
                System.out.println("线程"+(finalI +1)+"执行countDown");
                latch.countDown();
                System.out.println("线程"+(finalI +1)+"执行结束");
            });
        }
        latch.await();
        System.out.println("程序执行完成");
    }
}
