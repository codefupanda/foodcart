package com.foodcart.account.repository;

import com.foodcart.account.entity.AddressEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Optional<AddressEntity> getAddress(UUID accountId, UUID addressId) {
        Optional<AddressEntity> address = Optional.empty();
        try {
            AddressEntity singleResult = getEntityManager().createQuery("SELECT address FROM AccountEntity a join a.addressEntities address " +
                    " where a.id = :accountId AND address.id = :addressId", AddressEntity.class).getSingleResult();
            address = Optional.of(singleResult);
        } catch (NoResultException noResultException) {
            log.info("No address found");
        }
        return address;
    }
}
