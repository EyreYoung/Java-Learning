package org.seu.customRPC.serialization;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 基于Hessian序列化方式对{@code org.seu.customRPC.serialized.Serialization}的实现
 */
public class HessianSerialization implements Serialization {

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(os);
        hessianOutput.writeObject(obj);
        return os.toByteArray();
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        HessianInput hessianInput = new HessianInput(is);
        return (T) hessianInput.readObject(clazz);
    }
}
