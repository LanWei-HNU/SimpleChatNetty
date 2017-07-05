package com.lw.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * EventLoopGroup�����ڹ�����channel���߳��顣�ͺñ�һ�����ѭ��ʹ�õ��̳߳��й�����̡߳�
 * acceptor�߳��鸺�����ӵĴ����������ڽ��ܿͻ��˵�����
 * worker2�߳��鸺��handler��Ϣ���ݵĴ����������ڴ���SocketChannel�����д
 * ServerBootstrap:����֮����Ϊ��������������һЩ���á�
 * 
 * @author LanWei-HNU
 *
 */
public class ServerMain {

	// ����˶˿�
	private int port;

	public ServerMain(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new ServerMain(8088).run();
	}
	
	public void run(){
		// �����NIO�߳���
				EventLoopGroup acceptor = new NioEventLoopGroup(); // �������ӵĴ����������ڽ��ܿͻ��˵�����
				EventLoopGroup worker = new NioEventLoopGroup(); // ����handler��Ϣ���ݵĴ����������ڴ���SocketChannel�����д

				ServerBootstrap bootstrap = new ServerBootstrap();

				bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
				bootstrap.group(acceptor, worker); // ����ѭ���߳��飬ǰ�����ڴ���ͻ��������¼����������ڴ�������IO
				bootstrap.channel(NioServerSocketChannel.class); // ���ڹ���socketchannel����
				bootstrap.childHandler(new ServerIniterHandler()); // Ϊ����accept�ͻ��˵�channel�е�pipeline����Զ��崦����

				try {
					Channel channel = bootstrap.bind(port).sync().channel();// �󶨶˿ڣ�ʵ�����Ǵ���serversocketchannnel����ע�ᵽeventloop�ϣ���ͬ���ȴ���ɣ�������Ӧchannel
					System.out.println("server strart running in port:" + port);
					channel.closeFuture().sync();// �������������ȴ��ر�
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// �����˳�
					acceptor.shutdownGracefully();
					worker.shutdownGracefully();
				}
	}

}
