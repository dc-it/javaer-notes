package concurrent;

import java.util.List;
import java.util.concurrent.*;

/**
 * Future、FutureTask 异步计算任务 get获取计算结果
 *
 * 一般和Executor、Thread一起使用
 *
 * @author duchao
 */
public class FutureAndFutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("1获取原创数据前");
//        Future<List<Integer>> task = getDataFromRemoteTask();
//        System.out.println("1获取原创数据后");
//        System.out.println("1读取原创数据："+task.get());

        System.out.println("2获取原创数据前");
        FutureTask futureTask = new FutureTask(() -> getDataFromRemote());
        new Thread(futureTask).start();
        System.out.println("2获取原创数据后");
        System.out.println("2读取原创数据："+futureTask.get());
    }

    /**
     * 模拟从远程获取数据
     * @return
     */
    public static Future<List<Integer>> getDataFromRemoteTask() {
       return Executors.newFixedThreadPool(2).submit(() -> getDataFromRemote());
    }

    /**
     * 模拟从远程获取数据
     * @return
     */
    public static List<Integer> getDataFromRemote() throws InterruptedException {
        Thread.sleep(3000);
        return List.of(1,2,3);
    }
}
