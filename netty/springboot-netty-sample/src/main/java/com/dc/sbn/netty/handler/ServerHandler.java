package com.dc.sbn.netty.handler;

import com.dc.sbn.netty.invote.ActionMapUtil;
import com.dc.sbn.netty.message.Header;
import com.dc.sbn.netty.message.Message;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * netty服务器业务处理器
 *
 * @author duchao
 */
public class ServerHandler extends ChannelHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String content="我收到连接";
        Header header=new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "713f17ca614361fb257dc6741332caf2",content.getBytes("UTF-8").length, 1);
        Message message=new Message(header,content);
        ctx.writeAndFlush(message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message m = (Message) msg;
        /* 请求分发*/
        ActionMapUtil.invote(m.getHeader().getCammand(),ctx, m);
    }


}