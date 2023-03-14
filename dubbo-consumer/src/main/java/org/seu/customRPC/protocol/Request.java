package org.seu.customRPC.protocol;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 请求 消息体
 */
@Getter
@Setter
public class Request implements Serializable {

    private String serviceName; // 请求的Service名

    private String methodName; // 请求的方法名

    private Class[] argTypes; // 请求方法的参数类型

    private Object[] args; // 请求方法的参数

    public Request(String serviceName, String methodName, Object[] args) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = new Class[argTypes.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
    }
}
