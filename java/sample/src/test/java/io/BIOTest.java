package io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO  同步阻塞  面向流(字节流和字符流)  一个请求对应一个线程
 * <p>
 * 服务端会在accept阻塞
 * 客户端会在read阻塞
 *
 * @author duchao
 */
public class BIOTest {

    private static final int port = 1111;

    public static void main(String[] args) throws IOException {
        testIOClient();
    }

    public static void testIOClient() throws IOException {
        //模拟同时10个请求
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Socket socket = new Socket("127.0.0.1", port);

            //接收服务端数据：read阻塞，开启线程监听
            new Thread(()->{
                try {
                    InputStream inputStream = socket.getInputStream();
                    int len;
                    byte[] data = new byte[1024];
                    while ((len = inputStream.read(data)) != -1) {
                        System.out.println(String.format("客户端%d接收到：%s", finalI + 1,new String(data, 0, len)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            //向服务端发数据
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(String.format("客户端%d：你好，服务端！", finalI + 1).getBytes());
                outputStream.flush();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testIOServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        //模拟服务端最多只能同时只能处理5个请求
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        while (true) {
            Socket socket = serverSocket.accept();

            threadPool.execute(() -> {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("你好，客户端".getBytes());
                    outputStream.flush();

                    InputStream inputStream = socket.getInputStream();
                    int len;
                    byte[] data = new byte[1024];
                    //read阻塞，会导致线程资源被一直挂起，inputStream.close()会关闭socket连接
                    while ((len = inputStream.read(data)) != -1) {
                        System.out.println(new String(data, 0, len));

                        //模拟服务端处理很久才释放socket连接
                        Thread.sleep(10000);
                        inputStream.close();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            });
        }
    }
}
