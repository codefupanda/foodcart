package com.foodcart.account.vo;

import com.foodcart.account.domain.Id;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public record Account(String id,
                      String name,
                      List<PhoneNumber> phoneNumbers,
                      List<Address> addresses,
                      LocalDate createdOn) {

    public Account {
        phoneNumbers = CollectionUtils.isEmpty(phoneNumbers) ? Collections.emptyList() : phoneNumbers;
        addresses = CollectionUtils.isEmpty(addresses) ? Collections.emptyList() : addresses;
    }

    public static Account fromDomain(com.foodcart.account.domain.Account account) {
        return new Account(account.getId().getId().toString(),
                account.getName(),
                account.getPhoneNumbers().stream().map(PhoneNumber::fromDomain).collect(Collectors.toList()),
                account.getAddresses().stream().map(Address::fromDomain).collect(Collectors.toList()),
                account.getCreatedOn());
    }

    public com.foodcart.account.domain.Account toDomain() {
        com.foodcart.account.domain.Account domainAccount = new com.foodcart.account.domain.Account();
        if (this.id != null)
            domainAccount.setId(Id.fromId(UUID.fromString(this.id)));
        domainAccount.setName(this.name());
        domainAccount.setCreatedOn(this.createdOn());
        List<com.foodcart.account.domain.Address> addressEntities = this.addresses().stream().map(Address::toDomain).collect(Collectors.toList());
        domainAccount.setAddresses(addressEntities);
        List<com.foodcart.account.domain.PhoneNumber> phoneNumberEntities = this.phoneNumbers().stream().map(PhoneNumber::toDomain).collect(Collectors.toList());
        domainAccount.setPhoneNumbers(phoneNumberEntities);
        return domainAccount;
    }
}
