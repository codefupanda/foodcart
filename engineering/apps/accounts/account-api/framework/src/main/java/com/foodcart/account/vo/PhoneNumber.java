package com.foodcart.account.vo;

import com.foodcart.account.domain.PhoneNumberType;
import com.foodcart.account.domain.PhoneNumberVerification;

public record PhoneNumber(String countryCode, String phoneNumber, boolean primary, boolean verified) {

    public com.foodcart.account.domain.PhoneNumber toDomain() {
        com.foodcart.account.domain.PhoneNumber phoneNumberEntity = new com.foodcart.account.domain.PhoneNumber(this.countryCode, this.phoneNumber);
        phoneNumberEntity.setType(PhoneNumberType.PRIMARY); //TODO: handle this
        PhoneNumberVerification verification = new PhoneNumberVerification();
        verification.setVerified(verified);
        phoneNumberEntity.setVerification(verification);
        return phoneNumberEntity;
    }

    public static PhoneNumber fromDomain(com.foodcart.account.domain.PhoneNumber phoneNumber) {
        return new PhoneNumber(phoneNumber.getCountryCode(),
                phoneNumber.getPhoneNumber(),
                true,
                phoneNumber.getVerification().isVerified());
    }
}
