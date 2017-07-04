package com.lw.chat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * ��ô�����һ�����ɻ�����ͨ���У���Ϣ�кܶ������壬�����ļ���ͼƬ���ַ�������ô���ҵĿͻ��˷���һ��ͼƬ�����������
 * ������������֪���㷢����ͼƬ�أ�������漰���������Э��ͱ���롣
 * �ٶ����Ǽ��׶�������ֻ�������ַ������죬��ô�ͻ��˷��͵��ַ���������ֱ�Ӿ����ַ������ͳ�ȥ�ģ���·ֻ��ʶ��0��1��
 * ������Ƿ��ͳ�ȥ���ַ�����Ҫ��ĳ�ֱ����ʽ������ֽ������ٷ��ͳ�ȥ�����������ܵ����ǵ��ֽ�������ٰ�ĳ�������ʽ
 * ������ַ���������Լ�ȥʵ�������ı�������������ɼ��ؿ�����������neety�Ѿ��ṩ�ܶ��ֽ�����������ʹ�ã�
 * ��Stringencoder��StringDecoder����neety�ṩ�ı��������
 * 
 * @author LanWei-HNU
 * 2017��7��4��18:42:42
 */
public class ServerIniterHandler extends ChannelInitializer<SocketChannel> {
	/**
	 *  ͨ��ChannelInitializerȥ��channel��pipelie������ַ����������������������io���շ��ַ���
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
	    pipeline.addLast("docode",new StringDecoder());
	    pipeline.addLast("encode",new StringEncoder());
	    pipeline.addLast("chat",new ChatServerHandler ());
	}

}
