package com.lw.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 自定义的处理函类需要继承ChannelHandlerAdapter，我这里就直接继承SimpleChannelInboundHandler<T>，后面的泛型代表接受的消息的类型。
 * 
 * @author LanWei-HNU
 *
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String>{

	// 多个客户端连接进来，定义了一个ChannelGroup去存储连接进来的channel当作在线用户。
	public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 当channel的可读就绪时，该方法被调用，即是接收到客户端发过来的消息时被调用
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1)
	        throws Exception {
	    Channel channel = arg0.channel();
	    for (Channel ch : group) {
	        if (ch == channel) {
	            ch.writeAndFlush("[you]：" + arg1 + "\n");
	        } else {
	            ch.writeAndFlush(
	                    "[" + channel.remoteAddress() + "]: " + arg1 + "\n");
	        }
	    }

	}

	/**
	 * 当有客户端channel被连接进来时，该方法被调用
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    for (Channel ch : group) {
	        ch.writeAndFlush(
	                "[" + channel.remoteAddress() + "] " + "is comming");
	    }
	    group.add(channel);
	}

	/**
	 * 当有客户端channeld断开网络io，该方法被调用
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    for (Channel ch : group) {
	        ch.writeAndFlush(
	                "[" + channel.remoteAddress() + "] " + "is comming");
	    }
	    group.remove(channel);
	}

	/**
	 * 当有客户端channeld活跃时，该方法被调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    System.out.println("[" + channel.remoteAddress() + "] " + "online");
	}

	/**
	 * 当有客户端channeld不活跃时，该方法被调用。
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    Channel channel = ctx.channel();
	    System.out.println("[" + channel.remoteAddress() + "] " + "offline");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	        throws Exception {
	    System.out.println(
	            "[" + ctx.channel().remoteAddress() + "]" + "exit the room");
	    ctx.close().sync();
	}

}
