package org.seu.customRPC.registry;

import org.apache.catalina.util.ServerInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.util.List;
import java.util.stream.Collectors;

public class ZookeeperRegistry<T> implements Registry<T> {

    private InstanceSerializer serializer = new JsonInstanceSerializer(ServerInfo.class);

    private ServiceDiscovery<T> serviceDiscovery;

    private ServiceCache<T> serviceCache;

    private String address = "localhost:2181";

    public void start() throws Exception {
        String root = "/demo/rpc";

        // 初始化CuratorFramework
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                address, new ExponentialBackoffRetry(1000, 3));
        // 启动客户端
        client.start();
        // 阻塞当前线程，等待连接
        client.blockUntilConnected();
        client.createContainers(root);

        // 初始化ServiceDiscovery
        serviceDiscovery = ServiceDiscoveryBuilder
                .builder(ServerInfo.class)
                .client(client)
                .serializer(serializer)
                .build();
        // 启动ServiceDiscovery
        serviceDiscovery.start();

        // 创建ServiceCache，监听ZooKeeper相应节点的变化，方便后续读取
        serviceCache = serviceDiscovery.serviceCacheBuilder()
                .name(root)
                .build();
        serviceCache.start();

    }

    @Override
    public void registerService(ServiceInstance<T> service) throws Exception {
        serviceDiscovery.registerService(service);
    }

    @Override
    public void unregisterService(ServiceInstance<T> service) throws Exception {
        serviceDiscovery.unregisterService(service);
    }

    @Override
    public List<ServiceInstance<T>> queryForInstances(String name) throws Exception {
        return serviceCache.getInstances().stream()
                .filter(s -> name.equals(s.getName()))
                .collect(Collectors.toList());
    }
}
