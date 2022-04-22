package org.seu.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

public class BaseEvent<T> extends ApplicationEvent {

    private T eventData;

    public BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

    public T getEventData() {
        return eventData;
    }

}
