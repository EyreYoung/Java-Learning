package org.seu.customRPC.proxy;

import io.netty.channel.ChannelFuture;
import org.apache.curator.x.discovery.ServiceInstance;
import org.seu.customRPC.Constants;
import org.seu.customRPC.protocol.Header;
import org.seu.customRPC.protocol.Message;
import org.seu.customRPC.protocol.Request;
import org.seu.customRPC.registry.Registry;
import org.seu.customRPC.registry.ServerInfo;
import org.seu.customRPC.transport.Connection;
import org.seu.customRPC.transport.NettyResponseFuture;
import org.seu.customRPC.transport.RPCClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.seu.customRPC.Constants.MAGIC;
import static org.seu.customRPC.Constants.VERSION_1;

/**
 * 从 DemoRpcProxy 的实现中我们可以看到，它依赖了 ServiceInstanceCache 获取ZooKeeper 中注册的 Server 端地址，
 * 同时依赖了 DemoRpcClient 与Server 端进行通信，上层调用方拿到这个代理对象后，就可以像调用本地方法一样进行调用，
 * 而不再关心底层网络通信和服务发现的细节。当然，这个简易版 DemoRpcProxy 的实现还有很多可以优化的地方，例如：
 * <p>
 * - 缓存 DemoRpcClient 客户端对象以及相应的 Connection 对象，不必每次进行创建。
 * - 可以添加失败重试机制，在请求出现超时的时候，进行重试。
 * - 可以添加更加复杂和灵活的负载均衡机制，例如，根据 Hash 值散列进行负载均衡、根据节点 load 情况进行负载均衡等。
 * <p>
 * 你若感兴趣的话可以尝试进行扩展，以实现一个更加完善的代理层。
 */
public class RPCProxy implements InvocationHandler {

    // 需要代理的服务（接口）名称
    private String serviceName;

    private Map<Method, Header> headerCache = new ConcurrentHashMap<>();

    // 用于和ZooKeeper交互 自带缓存
    private Registry<ServerInfo> registry;

    public RPCProxy(String serviceName, Registry<ServerInfo> registry) throws Exception {
        this.serviceName = serviceName;
        this.registry = registry;
    }

    public static <T> T newInstance(Class<T> clazz, Registry<ServerInfo> registry) throws Exception {
        // 创建代理对象
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                new RPCProxy(clazz.getName(), registry));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据服务名从ZK缓存找到可用的Server地址
        List<ServiceInstance<ServerInfo>> serviceInstances = registry.queryForInstances(serviceName);
        // 从中随机挑选一个
        ServiceInstance<ServerInfo> serviceInstance =
                serviceInstances.get(ThreadLocalRandom.current().nextInt(serviceInstances.size()));

        // 创建请求消息
        String methodName = method.getName();
        Header header = headerCache.computeIfAbsent(method, h -> new Header(MAGIC, VERSION_1));
        Message message = new Message(header, new Request(serviceName, methodName, args));
        // call上面选中的Server端
        return remoteCall(serviceInstance.getPayload(), message);

    }

    protected Object remoteCall(ServerInfo serverInfo, Message message) throws Exception {
        if (serverInfo == null) {
            throw new RuntimeException("Getting available server error!");
        }

        // 创建Client连接指定的Server端
        RPCClient client = new RPCClient(serverInfo.getHost(), serverInfo.getPort());

        ChannelFuture channelFuture = client.connect().awaitUninterruptibly();
        // 创建对应的Connection并发送请求
        Connection connection = new Connection(channelFuture, true);
        NettyResponseFuture responseFuture = connection.request(message, Constants.DEFAULT_TIMEOUT);
        // 等待响应
        return responseFuture.getPromise().get(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

}
