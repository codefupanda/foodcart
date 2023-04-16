package com.foodcart.account.domain;


import com.foodcart.account.domain.specification.AccountSpecification;
import com.foodcart.account.domain.specification.AddressSpecification;
import com.foodcart.account.domain.specification.PhoneNumberSpecification;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Account {
    private Id id;
    private String name;
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private LocalDate createdOn;

    public void setAddresses(List<Address> addresses) {
        AddressSpecification addressSpecification = new AddressSpecification();
        addresses.forEach(addressSpecification::assertSatisfiedBy);

        this.addresses = new ArrayList<>(addresses);
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void addAddress(Address address) {
        AddressSpecification addressSpecification = new AddressSpecification();
        addressSpecification.assertSatisfiedBy(address);
        addresses.add(address);
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        PhoneNumberSpecification phoneNumberSpecification = new PhoneNumberSpecification();
        phoneNumbers.forEach(phoneNumberSpecification::assertSatisfiedBy);
        this.phoneNumbers = phoneNumbers;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return Collections.unmodifiableList(phoneNumbers);
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        PhoneNumberSpecification phoneNumberSpecification = new PhoneNumberSpecification();
        phoneNumberSpecification.assertSatisfiedBy(phoneNumber);
        this.phoneNumbers.add(phoneNumber);
    }

    public boolean isValid() {
        AccountSpecification accountSpecification = new AccountSpecification();
        return accountSpecification.isSatisfiedBy(this);
    }
}
