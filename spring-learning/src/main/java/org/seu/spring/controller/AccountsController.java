package org.seu.spring.controller;

import org.seu.spring.event.AccountCreatedEvent;
import org.seu.spring.event.AccountEventData;
import org.seu.spring.model.Account;
import org.seu.spring.repository.AccountRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.seu.spring.util.ResultVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/4
 */

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final ApplicationEventPublisher publisher;

    private final AccountRepository accountRepository;

    public AccountsController(ApplicationEventPublisher publisher, AccountRepository accountRepository) {
        this.publisher = publisher;
        this.accountRepository = accountRepository;
    }

    @PostMapping("")
    public ResultVO<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        publisher.publishEvent(new AccountCreatedEvent(this,
                new AccountEventData(savedAccount.getId(), savedAccount.getUsername())));
        return new ResultVO<Account>().success(savedAccount);
    }

    @GetMapping("")
    public ResultVO<List<Account>> listAccounts() {
        return new ResultVO<List<Account>>().success(accountRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResultVO<Account> getAccount(@PathVariable Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return new ResultVO<Account>().fail("account not found");
        }
        return new ResultVO<Account>().success(account.get());
    }

    @PutMapping("/{id}")
    public ResultVO<Account> updateAccount(@PathVariable Long id, @RequestBody Account request) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return new ResultVO<Account>().fail("account not found");
        }

        Account existingAccount = account.get();
        existingAccount.setUsername(request.getUsername());
        existingAccount.setEmail(request.getEmail());
        return new ResultVO<Account>().success(accountRepository.save(existingAccount));
    }

    @DeleteMapping("/{id}")
    public ResultVO<Void> deleteAccount(@PathVariable Long id) {
        if (!accountRepository.existsById(id)) {
            return new ResultVO<Void>().fail("account not found");
        }
        accountRepository.deleteById(id);
        return new ResultVO<Void>().success();
    }

}
