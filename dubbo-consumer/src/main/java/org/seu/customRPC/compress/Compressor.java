package org.seu.customRPC.compress;

import java.io.IOException;

/**
 * 压缩算法
 * 在有的场景中，请求或响应传输的数据比较大，直接传输比较消耗带宽，所以一般会采用压缩后再发送的方式。
 * 在前面介绍的 Demo RPC 消息头中的 extraInfo 字段中，就包含了标识消息体压缩方式的 bit 位。
 * 这里我们定义一个 Compressor 接口抽象所有压缩算法：
 */
public interface Compressor {

    byte[] compress(byte[] array) throws IOException;

    byte[] unCompress(byte[] array) throws IOException;

}
