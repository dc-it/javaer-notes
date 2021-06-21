package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * volatile
 * 可见性（一个线程内对变量的修改，直接反应到主存，其他线程获取的都是最新的值）
 * 不能保证原子性(操作互斥)
 *
 * @author duchao
 */
public class VolatileTest {

    private static volatile int count = 0;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(()->{
            count = 1;
        });
        for (int i = 0; i < 3; i++) {
            service.execute(()->{
                System.out.println(count);
            });
        }
    }

    public static int getCount() {
        return count;
    }


}
