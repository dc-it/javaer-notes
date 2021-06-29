package com.dc.sbn.netty.controller;

import com.dc.sbn.netty.core.ActionController;
import com.dc.sbn.netty.core.ActionMap;
import com.dc.sbn.netty.message.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * netty控制器
 *
 * @author duchao
 */
@ActionController
public class UserAction {

    @ActionMap(key=1)
    public String login(ChannelHandlerContext ct, Message message){
        System.out.println("用户登录");
        return "小明";
    }
}
