package com.foodcart.account.application.usecase;

import com.foodcart.account.domain.Account;
import com.foodcart.account.domain.Address;
import com.foodcart.account.domain.Id;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccount(Id accountId);

    Account save(Account account);

    Optional<Address> getAddress(Id accountId, Id addressId);

    Account saveAddress(Id accountId, Address address);
}
