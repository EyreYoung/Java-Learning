package org.seu.customRPC.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RPC消息头
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Header {

    private short magic; // 魔数

    private byte version; // 协议版本

    private byte extraInfo; // 附加信息(消息类型、序列化方式、压缩方式、请求类型)

    private Long messageId; // 消息ID

    private Integer size; // 消息体长度

}
