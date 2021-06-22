package io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO  同步非阻塞
 *
 * Channel通道
 * Buffer缓冲区
 * Selector选择器
 *
 * 通道：
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 *
 * 缓冲区：
 * ByteBuffer
 * CharBuffer
 * DoubleBuffer
 * FloatBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 * MappedByteBuffer
 *
 * 选择器：
 * 允许单线程处理多个 Channel
 *
 *
 * @author duchao
 */
public class NIOTest {

    private final static int port = 1111;

    public static void main(String[] args) {

    }

    public static void testIOClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",port));
    }

    @Test
    public void testIOServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.write(byteBuffer);
        }
    }

}
