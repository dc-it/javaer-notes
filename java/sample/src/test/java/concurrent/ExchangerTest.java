package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Exchanger 两个线程间数据交换
 *
 * 两个线程阻塞在exchange处交换了数据，继续向下执行
 *
 * @author duchao
 */
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                List<Integer> list = List.of(1,2);
                List<Integer> exList = exchanger.exchange(list);
                System.out.println("线程1-交换前数据："+list+"-交换后数据："+exList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                List<Integer> list = List.of(3,4,5);
                List<Integer> exList = exchanger.exchange(list);
                System.out.println("线程2-交换前数据："+list+"-交换后数据："+exList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
