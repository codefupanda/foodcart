package com.foodcart.account.domain.specification;

import com.foodcart.account.domain.PhoneNumber;

public class PhoneNumberSpecification extends AbstractSpecification<PhoneNumber> {

    @Override
    public boolean isSatisfiedBy(PhoneNumber phoneNumber) {
        return phoneNumber.getCountryCode() != null && phoneNumber.getCountryCode().length() > 2
                && phoneNumber.getPhoneNumber() != null; //TODO: Use phone number standardisation lib
    }
}
