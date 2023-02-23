package com.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariTest {
    public static void main(String[] args) {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("");
        cfg.setUsername("");
        cfg.setPassword("");
        cfg.addDataSourceProperty("cachePrepStmts", "true");
        cfg.addDataSourceProperty("prepStmtCacheSize", "250");
        HikariDataSource ds = new HikariDataSource(cfg);
        ds.close();
    }
}
