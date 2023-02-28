package org.seu.customRPC.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.seu.customRPC.Constants;
import org.seu.customRPC.compress.Compressor;
import org.seu.customRPC.compress.CompressorFactory;
import org.seu.customRPC.protocol.Header;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.serialization.Serialization;
import org.seu.customRPC.serialization.SerializationFactory;

/**
 * 自定义编码器
 */
public class RPCEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // 读取Message中的Header
        Header header = msg.getHeader();
        // 依次序列化 魔数 ｜ 版本 ｜ 附加信息 ｜ 消息ID
        out.writeShort(header.getMagic());
        out.writeByte(header.getVersion());
        out.writeByte(header.getExtraInfo());
        out.writeLong(header.getMessageId());
        Object content = msg.getContent();
        // 如果是心跳消息
        if (Constants.isHeartBeat(header.getExtraInfo())) {
            // 消息体写入0
            out.writeInt(0);
            return;
        }
        // 根据extraInfo确定 序列化方式 & 压缩方式
        Serialization serialization = SerializationFactory.get(header.getExtraInfo());
        Compressor compressor = CompressorFactory.get(header.getExtraInfo());
        // 序列化 & 压缩
        byte[] payload = compressor.compress(serialization.serialize(content));
        // 写入消息体长度
        out.writeInt(payload.length);
        // 写入消息体
        out.writeBytes(payload);
    }

}
