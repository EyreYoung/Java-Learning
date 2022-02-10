package com.core.threadlocal;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/8
 */

public class ThreadLocalTest {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        @SneakyThrows
        @Override
        protected Connection initialValue() {
            return DriverManager.getConnection("");
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);
    }

}
