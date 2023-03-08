package org.seu.customRPC.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.seu.customRPC.Constants;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Response;

/**
 * 在 Demo RPC 框架的 Client 端接收到响应消息的时候，也是先通过 DemoRpcDecoder 反序列化得到响应消息，
 * 之后通过一个自定义的 ChannelHandler（DemoRpcClientHandler）将响应返回给上层业务。
 */
public class RPCClientHandler extends SimpleChannelInboundHandler<Message<Response>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Response> msg) throws Exception {
        // 根据messageID拿出对应的Future
        NettyResponseFuture<Response> future =
                Connection.IN_FLIGHT_REQUEST_MAP.remove(msg.getHeader().getMessageId());
        Response response = msg.getContent();
        // 如果是心跳信息
        if (response == null && Constants.isHeartBeat(msg.getHeader().getExtraInfo())) {
            response = new Response();
            response.setCode(Constants.HEARTBEAT_CODE);
        }
        future.getPromise().setSuccess(response);
    }

}
