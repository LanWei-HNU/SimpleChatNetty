package com.lw.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * EventLoopGroup�����ڹ�����channel���߳��顣�ͺñ�һ�����ѭ��ʹ�õ��̳߳��й�����̡߳�
 * acceptor�߳��鸺�����ӵĴ����������ڽ��ܿͻ��˵�����
 * worker2�߳��鸺��handler��Ϣ���ݵĴ����������ڴ���SocketChannel�����д
 *	ServerBootstrap:����֮����Ϊ��������������һЩ���á�
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
		
		// �����NIO�߳���
		EventLoopGroup acceptor = new NioEventLoopGroup();	// �������ӵĴ����������ڽ��ܿͻ��˵�����
	    EventLoopGroup worker = new NioEventLoopGroup(); 	// ����handler��Ϣ���ݵĴ����������ڴ���SocketChannel�����д
	    
	    ServerBootstrap bootstrap = new ServerBootstrap();
	    
	    bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
	    bootstrap.group(acceptor, worker);					//����ѭ���߳��飬ǰ�����ڴ���ͻ��������¼����������ڴ�������IO
	    bootstrap.channel(NioServerSocketChannel.class);	//���ڹ���socketchannel����
	    bootstrap.childHandler(new ServerIniterHandler());	//Ϊ����accept�ͻ��˵�channel�е�pipeline����Զ��崦����
	}

}
