package com.foodcart.account.repository;

import com.foodcart.account.entity.AccountEntity;
import com.foodcart.account.entity.AddressEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends PanacheRepositoryBase<AccountEntity, UUID> {

    Optional<AddressEntity> getAddress(UUID accountId, UUID addressId);
}
