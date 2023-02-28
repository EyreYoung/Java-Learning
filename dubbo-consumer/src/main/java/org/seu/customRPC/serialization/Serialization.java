package org.seu.customRPC.serialization;

import java.io.IOException;

/**
 * RPC序列化接口
 * 注意，Request 和 Response 对象是要进行序列化的，需要实现 Serializable 接口。
 * 为了让这两个类的对象能够在 Client 和 Server 之间跨进程传输，
 * 需要进行序列化和反序列化操作，这里定义一个 Serialization 接口，统一完成序列化相关的操作：
 */
public interface Serialization {

    <T> byte[] serialize(T obj) throws IOException;

    <T> T deSerialize(byte[] data, Class<T> clazz) throws IOException;

}
