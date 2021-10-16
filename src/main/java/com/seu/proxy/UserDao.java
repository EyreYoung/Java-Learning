package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "UserDao")
public class UserDao implements IUserDao {
    @Override
    public void save() {
        log.debug("-----已经保存数据-----");
    }
}
