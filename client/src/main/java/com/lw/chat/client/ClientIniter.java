package com.lw.chat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 在channel的pipeline中添加编解码器和自定义消息处理器。
 * @author LanWei-HNU
 * 2017年7月5日09:53:38
 */
public class ClientIniter extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		pipeline.addLast("stringD", new StringDecoder());
		pipeline.addLast("stringC", new StringEncoder());
		pipeline.addLast("http", new HttpClientCodec());
		pipeline.addLast("chat", new ChatClientHandler());
	}

}
