package com.core.threadPool;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.slf4j.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadPoolTest {

    static TransmittableThreadLocal<String> traceIdContext = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(service);
        traceIdContext.set("trace-001");
        ttlExecutorService.submit(() -> System.out.println(traceIdContext.get()));
        ttlExecutorService.shutdown();
    }

}
