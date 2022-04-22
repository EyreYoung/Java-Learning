package org.seu.spring.event;

/**
 * @author slowdive
 * @summary 创建账号事件类
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

public class AccountCreatedEvent extends AccountEvent {
    /**
     * 构造函数
     *
     * @param source    触发事件的对象
     * @param eventData 对象携带的信息
     */
    public AccountCreatedEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}
