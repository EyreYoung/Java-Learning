package org.seu.customRPC.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.seu.customRPC.Constants;
import org.seu.customRPC.codec.RPCDecoder;
import org.seu.customRPC.codec.RPCEncoder;

public class RPCServer {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerBootstrap serverBootstrap;

    private Channel channel;

    protected int port;

    public RPCServer (int port) throws InterruptedException {
        this.port = port;

        bossGroup = NettyEventLoopFactory.eventLoopGroup(1, "Boss");
        workerGroup = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "Worker");
        serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NettyEventLoopFactory.serverSocketChannelClass())
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("DEMO RPC Decoder",
                                new RPCDecoder());
                        ch.pipeline().addLast("DEMO RPC Encoder",
                                new RPCEncoder());
                        ch.pipeline().addLast("Server Handler",
                                new RPCServerHandler());
                    }
                });
    }

    public ChannelFuture start() throws InterruptedException {
        ChannelFuture channelFuture = serverBootstrap.bind(port);
        Channel channel = channelFuture.channel();
        channel.closeFuture();
        return channelFuture;
    }

}
