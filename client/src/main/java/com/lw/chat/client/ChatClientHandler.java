package com.lw.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 打印服务器消息
 * @author LanWei-HNU
 * 2017年7月5日09:45:54
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		System.out.println(arg1);
	}

}
