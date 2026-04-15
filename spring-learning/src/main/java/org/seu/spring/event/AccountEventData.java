package org.seu.spring.event;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

public class AccountEventData {

    private final Long accountId;

    private final String username;

    public AccountEventData(Long accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

}
