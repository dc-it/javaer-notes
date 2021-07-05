## Netty

异步非阻塞的、事件驱动的网络应用程序框架和工具。   
JBoss做的Jar。   
快速开发高性能、高可靠性的网络服务器和客户端程序。   
RocketMQ、Dubbo等使用Netty作为底层网络通讯。   
并发高、传输快、封装好。   
传输快原因：nio特性之一--零拷贝，ByteBuf直接从磁盘读到堆内存

test

参考资料：
- [netty-5.0.0.Alpha2官方例子](https://github.com/netty/netty/blob/netty-5.0.0.Alpha2/example/src/main/java/io/netty/example)
- [基于netty游戏服后台搭建](https://blog.csdn.net/h348592532/article/details/52816148)
- [SpringBoot2+Netty+WebSocket(netty实现websocket，支持URL参数)](https://blog.csdn.net/moshowgame/article/details/91552993)
- [netty与websocket通信demo](https://my.oschina.net/tangcoffee/blog/338562)
- [Netty实现自定义协议](https://my.oschina.net/zhangxufeng/blog/3043768)