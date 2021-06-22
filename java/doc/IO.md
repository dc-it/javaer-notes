## IO
### 概念
- 同步
- 异步
- 阻塞
- 非阻塞

### 几类IO
- BIO 同步阻塞 面向流(字节流和字符流)的 一个线程处理一个请求/连接
- NIO 同步非阻塞 面向块(Channel通道和Buffer缓冲区)的 一个线程处理多个请求/连接（通道） 
      netty
- AIO 异步非阻塞 Proactor设计模式
- IO Multiplexing 多路复用IO 阻塞 Reactor设计模式


参考：
- [Java NIO系列教程](https://ifeve.com/java-nio-all/)
