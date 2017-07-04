package com.lw.chat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 那么，大家一定有疑惑，网络通信中，消息有很多种载体，例如文件，图片，字符串，那么在我的客户端发送一个图片给你服务器，
 * 服务器又怎样知道你发的是图片呢？里面就涉及到两项技术，协议和编解码。
 * 假定我们简易多人聊天只允许发送字符串聊天，那么客户端发送的字符串不可能直接就是字符串发送出去的，电路只能识别0和1，
 * 因此我们发送出去的字符串需要按某种编码格式编码成字节数组再发送出去，服务器接受到我们的字节数组后，再按某个解码格式
 * 解码成字符串。如果自己去实现这样的编码解码器，无疑加重开发工作量。neety已经提供很多种解码器给我们使用，
 * 而Stringencoder和StringDecoder就是neety提供的编解码器。
 * 
 * @author LanWei-HNU
 * 2017年7月4日18:42:42
 */
public class ServerIniterHandler extends ChannelInitializer<SocketChannel> {
	/**
	 *  通过ChannelInitializer去给channel的pipelie添加上字符串编解码器。即可在网络io中收发字符串
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
	    pipeline.addLast("docode",new StringDecoder());
	    pipeline.addLast("encode",new StringEncoder());
	    pipeline.addLast("chat",new ChatServerHandler ());
	}

}
