package org.seu.customRPC.transport;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.seu.customRPC.protocol.Message;

@Getter
@Setter
@AllArgsConstructor
public class NettyResponseFuture<T> {

    private long createTime;

    private long timeout;

    private Message request;

    private Channel channel;

    private Promise<T> promise;

}
