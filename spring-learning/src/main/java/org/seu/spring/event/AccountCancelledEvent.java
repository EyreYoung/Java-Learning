package org.seu.spring.event;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

public class AccountCancelledEvent extends BaseEvent<AccountEventData> {
    public AccountCancelledEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}
