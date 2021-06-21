package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 线程屏障
 * 设定数量的线程都await阻塞才会全部唤醒继续执行，内部count是进行+1操作，直到达到设定量全部唤醒继续执行
 *
 * @author duchao
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        int count = 4;
        CyclicBarrier barrier = new CyclicBarrier(count+1);
        ExecutorService service = Executors.newFixedThreadPool(count);
        for (int i = 1; i < count+1; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    barrier.await();
                    System.out.println("线程" + finalI + "执行...");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        barrier.await();
        System.out.println("主线程执行...");
    }
}
