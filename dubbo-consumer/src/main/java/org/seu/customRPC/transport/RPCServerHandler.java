package org.seu.customRPC.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.seu.customRPC.Constants;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Request;

import java.util.concurrent.*;

/**
 * 正如前文介绍 Netty 线程模型的时候提到，我们不能在 Netty 的 I/O 线程中执行耗时的业务逻辑。
 * 在 Demo RPC 框架的 Server 端接收到请求时，首先会通过上面介绍的 DemoRpcDecoder 反序列化得到请求消息，
 * 之后我们会通过一个自定义的 ChannelHandler（DemoRpcServerHandler）将请求提交给业务线程池进行处理。
 */
public class RPCServerHandler extends SimpleChannelInboundHandler<Message<Request>> {

    static Executor executor = Executors.newCachedThreadPool();

    // 创建业务线程池
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2, 4, 10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Request> msg) throws Exception {
        byte extraInfo = msg.getHeader().getExtraInfo();
        // 如果是心跳信息
        if (Constants.isHeartBeat(extraInfo)) {
            // 直接返回
            ctx.writeAndFlush(msg);
            return;
        }
        // 如果不是心跳信息，扔到线程池执行业务代码
        threadPoolExecutor.execute(new InvokeRunnable(ctx, msg));
    }

}
