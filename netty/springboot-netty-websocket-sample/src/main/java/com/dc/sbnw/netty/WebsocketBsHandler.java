package com.dc.sbnw.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * Websocket业务处理器
 *
 * @author duchao
 */
@Slf4j
public class WebsocketBsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public final static ChannelGroup channelGroup;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端{}建立连接",ctx.channel().remoteAddress());
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端{}断开连接",ctx.channel().remoteAddress());
        channelGroup.remove(ctx.channel());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("收到客户端{}的数据：{}",ctx.channel(),msg.text());
        sendBroadcastMsg(ctx,msg);
    }

    private void sendP2pMsg(TextWebSocketFrame msg){

    }

    private void sendBroadcastMsg(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        channelGroup.writeAndFlush(new TextWebSocketFrame(msg.text()));
    }
}
