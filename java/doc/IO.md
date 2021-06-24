## IO
### 概念
（1）同步和异步区别

同步和异步描述的是一种消息通知的机制，主动等待消息返回还是被动接受消息。
同步io指的是调用方通过主动等待获取调用返回的结果来获取消息通知。
而异步io指的是被调用方通过某种方式（如，回调函数）来通知调用方获取消息。

（2）阻塞和非阻塞区别
阻塞和非阻塞描述的是调用方在获取消息过程中的状态，阻塞等待还是立刻返回。
阻塞io指的是调用方在获取消息的过程中会挂起阻塞，直到获取到消息。
而非阻塞io指的是调用方在获取io的过程中会立刻返回而不进行挂起。

### 几类IO
- BIO 同步阻塞 面向流(字节流和字符流)的 一个线程处理一个请求/连接
- NIO 同步非阻塞 面向块(Channel通道和Buffer缓冲区)的 一个线程处理多个请求/连接（通道） 事件驱动  IO多路复用
      netty封装nio
- AIO 异步非阻塞 Proactor设计模式 时间驱动

> IO Multiplexing IO多路复用 阻塞 Reactor设计模式

### BIO
  
  服务端会在accept阻塞
  
  客户端会在read阻塞

### NIO
  
  Channel通道
  Buffer缓冲区
  Selector选择器
  
- 通道：
  FileChannel
  DatagramChannel
  SocketChannel
  ServerSocketChannel
  
- 缓冲区：
  ByteBuffer
  CharBuffer
  DoubleBuffer
  FloatBuffer
  IntBuffer
  LongBuffer
  ShortBuffer
  MappedByteBuffer
  
- 选择器：
  允许单线程处理多个 Channel
 
- 管道Pipe：
  线程间单向通道
  Sink通道进/写入
  Source通道出/读出

与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。

参考：
- [Java NIO系列教程](https://ifeve.com/java-nio-all/)
- [BIO，NIO，多路复用，AIO](https://blog.csdn.net/qq_33330687/article/details/81558198)
- [Java提供了哪些IO方式？ NIO如何实现多路复用？](https://www.jianshu.com/p/35aff07041fa)
- [多路复用器Selector](https://www.cnblogs.com/duanxz/p/6782783.html)