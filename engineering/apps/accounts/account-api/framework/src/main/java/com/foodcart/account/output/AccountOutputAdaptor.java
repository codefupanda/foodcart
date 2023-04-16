package com.foodcart.account.output;


import com.foodcart.account.application.port.output.AccountOutputPort;
import com.foodcart.account.domain.Account;
import com.foodcart.account.domain.Address;
import com.foodcart.account.domain.Id;
import com.foodcart.account.entity.AccountEntity;
import com.foodcart.account.entity.AddressEntity;
import com.foodcart.account.repository.AccountRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class AccountOutputAdaptor implements AccountOutputPort {
    private final AccountRepository accountRepository;

    public AccountOutputAdaptor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findByIdOptional(Id accountId) {
        accountRepository.findAll().stream().forEach(accountEntity -> System.out.println("id -> " + accountEntity.getId()));
        Optional<AccountEntity> accountEntityOptional = accountRepository.findByIdOptional(accountId.getId());
        return accountEntityOptional.map(AccountEntity::toDomain);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        AccountEntity accountEntity = AccountEntity.fromDomain(account);
        accountRepository.persist(accountEntity);
        accountRepository.flush();
        return accountEntity.toDomain();
    }

    @Override
    public Optional<Address> getAddress(Id accountId, Id addressId) {
        Optional<AddressEntity> address = accountRepository.getAddress(accountId.getId(), addressId.getId());
        return address.map(AddressEntity::toDomain);
    }

}
