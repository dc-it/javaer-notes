package com.dc.sbn.netty.server;

import com.dc.sbn.netty.decoder.MessageDecoder;
import com.dc.sbn.netty.encoder.MessageEncoder;
import com.dc.sbn.netty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * netty服务器
 *
 * @author duchao
 */
@Slf4j
@Component
public class TimeServer implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${netty.port}")
    private int port;

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ByteBuf heapBuffer = Unpooled.buffer(8);
        heapBuffer.writeBytes("\r".getBytes());
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("encoder", new MessageEncoder()).addLast("decoder", new MessageDecoder()).addFirst(new LineBasedFrameDecoder(65535))
                                    .addLast(new ServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            if(f.isSuccess()){
                log.info("netty服务器启动成功，监听端口{}",port);
            }
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("netty服务器启动失败");
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        this.run();
    }
}
