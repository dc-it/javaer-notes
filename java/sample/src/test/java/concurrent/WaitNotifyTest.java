package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wait、Notify 线程通信
 *
 * notify只是唤醒同个锁对象wait阻塞的线程进入等待队列，让其进入就绪状态，但是并不释放锁+cpu时间片。
 * wait操作就是自我阻塞，让出锁和cpu执行时间片。
 *
 * @author duchao
 */
public class WaitNotifyTest {

    private int count = 0;

    public synchronized void testWait() {
        while (true) {
            try {
                if(count==11){
                    Thread.interrupted();
                }
                System.out.println("wait前执行");
                this.wait();
                System.out.println("wait后执行");
                this.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void testNotify() {
        while (count<10) {
            try {
                System.out.println("notify前执行");
                this.notify();
                System.out.println("notify后执行");
                count++;
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyTest test = new WaitNotifyTest();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> { test.testWait(); });
        service.execute(() -> { test.testNotify(); });
    }
}
