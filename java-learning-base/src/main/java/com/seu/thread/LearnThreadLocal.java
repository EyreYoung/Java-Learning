package com.seu.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author slowdive
 * @version v1
 * @summary
 * @since 2020/8/18 3:09 下午
 */
@Slf4j(topic = "学习ThreadLocal")
public class LearnThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        executors.execute(()->{
            System.out.println(Thread.currentThread().getName() + LocalThread.getConnection());
        });
        executors.execute(()->{
            System.out.println(Thread.currentThread().getName() + LocalThread.getConnection());
        });
        System.out.println(Thread.currentThread().getName() + LocalThread.getConnection());
        executors.shutdown();
    }
}

@Slf4j
class LocalThread {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection(){
        Connection connection = threadLocal.get();
        if(null == connection) {
            connection = DataSource.get();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void remove() {
        threadLocal.remove();
    }

    private static class Connection {
        String name;

        public Connection(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static class DataSource {
        static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(5);
        static {
            connections.offer(new Connection("1"));
            connections.offer(new Connection("2"));
            connections.offer(new Connection("3"));
            connections.offer(new Connection("4"));
            connections.offer(new Connection("5"));
        }

        static Connection get() {
            Connection connection = null;
            try {
                connection = connections.take();
                connections.put(connection);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
