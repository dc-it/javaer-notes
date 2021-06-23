package io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * NIO  同步非阻塞
 * <p>
 * Channel通道
 * Buffer缓冲区
 * Selector选择器
 * <p>
 * 通道：
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 * <p>
 * 缓冲区：
 * ByteBuffer
 * CharBuffer
 * DoubleBuffer
 * FloatBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 * MappedByteBuffer
 * <p>
 * 选择器：
 * 允许单线程处理多个 Channel
 *
 * @author duchao
 */
public class NIOTest {

    private final static int port = 1111;

    public static void main(String[] args) throws Exception {
        //阻塞式，没有使用选择器
        //testIOBlockClient();

        //非阻塞式，有选择器
        testIONonBlockClient();
    }


    public static void testIONonBlockClient() throws IOException {
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",port));

        // 监听连接
        socketChannel.register(selector,SelectionKey.OP_CONNECT);

        while (true){
            selector.select();

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                //连接就绪
                if(selectionKey.isConnectable()){
                    System.out.println("客户端连接就绪...");
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);

                    channel.write(ByteBuffer.wrap("hello server".getBytes()));

                    channel.register(selector,SelectionKey.OP_READ);
                }
                //读就绪
                else if(selectionKey.isReadable()){
                    System.out.println("客户端读数据就绪...");
                    SocketChannel channel = (SocketChannel)selectionKey.channel();

                    // 从通道读取数据到缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);

                    System.out.println(new String(byteBuffer.array()));
                }
            }
        }
    }

    @Test
    public void testIONonBlockServer() throws IOException {

        //通道选择器
        Selector selector = Selector.open();

        //服务端tcp套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        //通道注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //事件到达会返回继续执行，否则一直阻塞
            selector.select();

            //监听到达的事件
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
            while (selectionKeyIterator.hasNext()){
                //获取监听到达事件
                SelectionKey selectionKey = selectionKeyIterator.next();

                //移除事件，避免重复处理
                selectionKeyIterator.remove();

                //请求连接就绪
                if(selectionKey.isAcceptable()){
                    System.out.println("服务端接受请求就绪...");
                    ServerSocketChannel serverSocketChannel2 = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel2.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.write(ByteBuffer.wrap("hello client".getBytes()));

                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                //套接字读数据就绪
                else if(selectionKey.isReadable()){
                    System.out.println("服务端接受读数据就绪...");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    //从通道读数据到缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);

                    System.out.println(new String(byteBuffer.array()));
                }
            }
        }
    }

    /**
     * 客户端（阻塞）
     * @throws IOException
     */
    public static void testIOBlockClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",port));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.next();
            byteBuffer.put(line.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }

    /**
     * 服务端（阻塞）
     * @throws IOException
     */
    @Test
    public void testIOBlockServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            try {
                System.out.println("客户端请求进来...");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (socketChannel.read(byteBuffer) != -1) {
                    System.out.println(new String(byteBuffer.array()));
                    byteBuffer.clear();
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

}
