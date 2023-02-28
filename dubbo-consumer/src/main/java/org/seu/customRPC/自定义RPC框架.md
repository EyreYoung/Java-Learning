# 自定义RPC框架实现
## 框架结构

protocol：简易版 RPC 框架的自定义协议。 

serialization：提供了自定义协议对应的序列化、反序列化的相关工具类。

codec：提供了自定义协议对应的编码器和解码器。

transport：基于 Netty 提供了底层网络通信的功能，其中会使用到 codec 包中定义编码器和解码器，以及 serialization 包中的序列化器和反序列化器。

registry：基于 ZooKeeper 和 Curator 实现了简易版本的注册中心功能。

proxy：使用 JDK 动态代理实现了一层代理。


## 步骤
1. 序列化｜反序列化
2. 压缩｜解压缩
3. 编码｜解码