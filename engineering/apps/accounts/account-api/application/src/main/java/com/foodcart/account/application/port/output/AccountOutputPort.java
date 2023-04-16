package com.foodcart.account.application.port.output;

import com.foodcart.account.domain.Account;
import com.foodcart.account.domain.Address;
import com.foodcart.account.domain.Id;

import java.util.Optional;

public interface AccountOutputPort {
    Optional<Account> findByIdOptional(Id accountId);

    Account save(Account account);

    Optional<Address> getAddress(Id accountId, Id addressId);
}
