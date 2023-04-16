package com.foodcart.account.application.port.input;

import com.foodcart.account.application.port.output.AccountOutputPort;
import com.foodcart.account.application.usecase.AccountService;
import com.foodcart.account.domain.Account;
import com.foodcart.account.domain.Address;
import com.foodcart.account.domain.Id;
import com.foodcart.account.domain.specification.AccountSpecification;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final AccountOutputPort accountOutputPort;

    public AccountServiceImpl(AccountOutputPort accountOutputPort) {
        this.accountOutputPort = accountOutputPort;
    }

    @Override
    public Optional<Account> getAccount(Id accountId) {
        return accountOutputPort.findByIdOptional(accountId);
    }

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(Id.newId());
        }

        AccountSpecification accountSpecification = new AccountSpecification();
        accountSpecification.assertSatisfiedBy(account);
        return accountOutputPort.save(account);
    }

    @Override
    public Optional<Address> getAddress(Id accountId, Id addressId) {
        return accountOutputPort.getAddress(accountId, addressId);
    }

    @Override
    public Account saveAddress(Id accountId, Address address) {
        Optional<Account> accountOptional = getAccount(accountId);
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Account Not Found"); //TODO: Create exceptions
        }

        Account account = accountOptional.get();
        account.addAddress(address);
        return accountOutputPort.save(account);
    }

}
