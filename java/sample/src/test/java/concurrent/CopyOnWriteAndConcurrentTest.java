package concurrent;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发容器：线程安全+并发性（尽量不用锁）
 * 主要CopyOnWrite和Concurrent几个开头的容器
 *
 * CopyOnWrite 读多写少   读写锁   写锁存在时互斥，读锁可并发。写时创建了一次容器
 * Concurrent  cas 不加锁  写时不影响读
 *
 *
 *
 * @author duchao
 */
public class CopyOnWriteAndConcurrentTest {

    @Test
    public void testCopyOnWrite(){
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(()->System.out.println(list));
        service.execute(()-> System.out.println(list));
        service.execute(()->list.add(new Random().nextInt(10)));
        service.execute(()-> System.out.println(list.get(0)));
        service.execute(()-> System.out.println(list));

    }

    @Test
    public void testConcurrentHashMap(){
        ConcurrentHashMap<Integer, Object> map = new ConcurrentHashMap<>();
        map.put(1,1);
    }
}
