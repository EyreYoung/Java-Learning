package org.seu.customRPC.protocol;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应 消息体
 */
@Getter
@Setter
public class Response implements Serializable {

    private int code = 0; // 响应的错误码

    private String errMsg; // 异常信息

    private Object result; // 响应结果

}
