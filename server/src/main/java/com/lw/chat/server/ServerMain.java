package com.lw.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * EventLoopGroup：用于管理多个channel的线程组。就好比一组进行循环使用的线程池中管理的线程。
 * acceptor线程组负责连接的处理，即是用于接受客户端的连接
 * worker2线程组负责handler消息数据的处理，即是用于处理SocketChannel网络读写
 *	ServerBootstrap:简言之就是为服务器启动进行一些配置。
 * @author LanWei-HNU
 *
 */
public class ServerMain {
	
	// 服务端端口
	private int port;

	public ServerMain(int port) {
		this.port = port;
	}


	public static void main(String[] args) {
		
		// 服务端NIO线程组
		EventLoopGroup acceptor = new NioEventLoopGroup();	// 负责连接的处理，即是用于接受客户端的连接
	    EventLoopGroup worker = new NioEventLoopGroup(); 	// 负责handler消息数据的处理，即是用于处理SocketChannel网络读写
	    
	    ServerBootstrap bootstrap = new ServerBootstrap();
	    
	    bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
	    bootstrap.group(acceptor, worker);					//设置循环线程组，前者用于处理客户端连接事件，后者用于处理网络IO
	    bootstrap.channel(NioServerSocketChannel.class);	//用于构造socketchannel工厂
	    bootstrap.childHandler(new ServerIniterHandler());	//为处理accept客户端的channel中的pipeline添加自定义处理函数
	}

}
