package org.seu.customRPC.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.seu.customRPC.Constants;
import org.seu.customRPC.compress.Compressor;
import org.seu.customRPC.compress.CompressorFactory;
import org.seu.customRPC.protocol.Header;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Request;
import org.seu.customRPC.serialization.Serialization;
import org.seu.customRPC.serialization.SerializationFactory;

import java.util.List;

/**
 * 自定义解码器
 */
public class RPCDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 如果读取字节流大小 < 16，无法解析消息头，不读取
        if (in.readableBytes() < Constants.HEADER_SIZE)
            return;
        // 记录当前readIndex的位置，方便重置
        in.markReaderIndex();
        // 读取消息头的魔数部分
        short magic = in.readShort();
        // 如果魔数不匹配
        if (magic != Constants.MAGIC) {
            // 重置readIndex指针
            in.resetReaderIndex();
            // 抛出异常
            throw new RuntimeException("Magic Number ERROR: " + magic);
        }
        // 依次读取消息头中的数据
        byte version = in.readByte(); // 消息版本
        byte extraInfo = in.readByte(); // 附加信息
        long messageId = in.readLong(); // 消息ID
        int size = in.readInt(); // 消息体长度
        Object request = null;
        // 如果不是心跳消息
        if (!Constants.isHeartBeat(extraInfo)) {
            // 接下来的可读字节数量小于消息体长度的话
            if (in.readableBytes() < size) {
                // 重置指针并返回
                in.resetReaderIndex();
                return;
            }
            // 初始化消息体
            byte[] payload = new byte[size];
            // 读取消息体
            in.readBytes(payload);
            // 根据extraInfo选取 反序列化方式 和 压缩方式
            Serialization serialization = SerializationFactory.get(extraInfo);
            Compressor compressor = CompressorFactory.get(extraInfo);
            // 解压缩 & 反序列化
            request = serialization.deSerialize(
                    compressor.unCompress(payload),
                    Request.class);
            // 拼装 Header 和 Message
            Header header = new Header(magic, version, extraInfo, messageId, size);
            Message message = new Message<>(header, request);
            // 向后传递
            out.add(message);
        }
    }

}
