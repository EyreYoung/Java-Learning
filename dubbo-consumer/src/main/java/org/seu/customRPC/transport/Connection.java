package org.seu.customRPC.transport;

import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Request;
import org.seu.customRPC.protocol.Response;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Connection implements Closeable {

    // 消息ID生成
    private static AtomicLong ID_GENERATOR = new AtomicLong(0);

    //
    public static Map<Long, NettyResponseFuture<Response>> IN_FLIGHT_REQUEST_MAP = new ConcurrentHashMap<>();

    private ChannelFuture future;

    private AtomicBoolean isConnected = new AtomicBoolean();

    public NettyResponseFuture<Response> request(Message<Request> msg, long timeOut) {
        // 生成 并 设置 消息ID
        long messageId = ID_GENERATOR.incrementAndGet();
        msg.getHeader().setMessageId(messageId);

        // 创建消息关联的Future
        NettyResponseFuture responseFuture = new NettyResponseFuture(
                System.currentTimeMillis(),
                timeOut,
                msg,
                future.channel(),
                new DefaultPromise(new DefaultEventLoop()));

        // 将消息ID和关联的Future放到Map中
        IN_FLIGHT_REQUEST_MAP.put(messageId, responseFuture);
        try {
            // 发送消息
            future.channel().writeAndFlush(msg);
        } catch (Exception e) {
            // 发生异常时 Map中移除该Future
            IN_FLIGHT_REQUEST_MAP.remove(messageId);
            throw e;
        }
        return responseFuture;
    }

    @Override
    public void close() throws IOException {
        future.channel().close();
    }


    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }

    public boolean getIsConnected() {
        return isConnected.get();
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected.set(isConnected);
    }

    public Connection(ChannelFuture future, boolean isConnected) {
        this.future = future;
        this.isConnected.set(isConnected);
    }

}
