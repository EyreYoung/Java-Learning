package org.seu.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

@Controller
public class AccountsController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("")
    private Account createAccount(@RequestBody Account account) {
        // ...
        publisher.publishEvent(new AccountCreatedEvent(this, new AccountEventData()));
        return account;
    }

}