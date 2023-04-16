package com.foodcart.account.entity;

import com.foodcart.account.domain.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<PhoneNumberEntity> phoneNumberEntities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<AddressEntity> addressEntities;

    @Column(name = "created_on")
    private LocalDate createdOn;

    public static AccountEntity fromDomain(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(account.getName());
        accountEntity.setCreatedOn(account.getCreatedOn());
        List<AddressEntity> addressEntities = account.getAddresses().stream().map(AddressEntity::fromDomain).collect(Collectors.toList());
        addressEntities.forEach(a -> a.setAccountEntity(accountEntity));
        accountEntity.setAddressEntities(addressEntities);
        List<PhoneNumberEntity> phoneNumberEntities = account.getPhoneNumbers().stream().map(PhoneNumberEntity::fromDomain).collect(Collectors.toList());
        phoneNumberEntities.forEach(a -> a.setAccountEntity(accountEntity));
        accountEntity.setPhoneNumberEntities(phoneNumberEntities);
        return accountEntity;
    }

    public com.foodcart.account.domain.Account toDomain() {
        com.foodcart.account.domain.Account domainAccount = new com.foodcart.account.domain.Account();
        domainAccount.setId(com.foodcart.account.domain.Id.fromId(this.id));
        domainAccount.setName(this.getName());
        domainAccount.setCreatedOn(this.getCreatedOn());
        List<com.foodcart.account.domain.Address> addressEntities = this.getAddressEntities().stream().map(AddressEntity::toDomain).collect(Collectors.toList());
        domainAccount.setAddresses(addressEntities);
        List<com.foodcart.account.domain.PhoneNumber> phoneNumberEntities = this.getPhoneNumberEntities().stream().map(PhoneNumberEntity::toDomain).collect(Collectors.toList());
        domainAccount.setPhoneNumbers(phoneNumberEntities);
        return domainAccount;
    }
}
