package com.seu.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "UserDaoProxy")
public class UserDaoProxy implements IUserDao {
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }

    @Override
    public void save() {
        log.debug("开始事务-----");
        target.save();
        log.debug("结束事务-----");
    }
}
