package org.seu.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author slowdive
 * @summary 抽象的账号相关事件类
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

public abstract class AccountEvent extends ApplicationEvent {

    private final AccountEventData eventData;

    /**
     * 构造函数
     *
     * @param source    触发事件的对象
     * @param eventData 对象携带的信息
     */
    public AccountEvent(Object source, AccountEventData eventData) {
        super(source);
        this.eventData = eventData;
    }

    public AccountEventData getEventData() {
        return eventData;
    }

}
