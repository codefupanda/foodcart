package com.foodcart.account.config;

import com.foodcart.account.application.port.input.AccountServiceImpl;
import com.foodcart.account.application.port.output.AccountOutputPort;
import com.foodcart.account.application.usecase.AccountService;
import jakarta.enterprise.inject.Produces;

public class Configurations {

    @Produces
    public AccountService accountService(AccountOutputPort repo) {
        return new AccountServiceImpl(repo);
    }
}
