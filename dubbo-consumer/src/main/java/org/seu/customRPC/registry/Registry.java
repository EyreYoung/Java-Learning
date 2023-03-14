package org.seu.customRPC.registry;

import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;

public interface Registry<T> {

    void registerService(ServiceInstance<T> instance) throws Exception;

    void unregisterService(ServiceInstance<T> instance) throws Exception;

    List<ServiceInstance<T>> queryForInstances(String name) throws Exception;

}
