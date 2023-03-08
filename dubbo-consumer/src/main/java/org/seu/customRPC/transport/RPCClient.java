package org.seu.customRPC.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import org.seu.customRPC.Constants;
import org.seu.customRPC.codec.RPCDecoder;
import org.seu.customRPC.codec.RPCEncoder;

import java.io.Closeable;
import java.io.IOException;

public class RPCClient implements Closeable {

    private final String host;

    private final int port;

    Bootstrap clientBootstrap;

    EventLoopGroup group;

    public RPCClient(String host, int port) {
        this.host = host;
        this.port = port;
        clientBootstrap = new Bootstrap();

        // 创建并配置客户端
        group = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "Netty Client Worker");
        clientBootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NettyEventLoopFactory.socketChannelClass())
                // 指定Handler顺序
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("DEMO RPC Encoder", new RPCEncoder());
                        ch.pipeline().addLast("DEMO RPC Decoder", new RPCDecoder());
                        ch.pipeline().addLast("Client Handler", new RPCClientHandler());
                    }
                });
    }

    public ChannelFuture connect() {
        ChannelFuture connect = clientBootstrap.connect(host, port);
        connect.awaitUninterruptibly();
        return connect;
    }

    @Override
    public void close() throws IOException {
        group.shutdownGracefully();
    }

}
