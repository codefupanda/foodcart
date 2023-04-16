package com.foodcart.account.domain;

import com.foodcart.account.domain.specification.PhoneNumberSpecification;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PhoneNumber implements Serializable {

    private String countryCode;
    private String phoneNumber;
    private PhoneNumberVerification verification;
    private PhoneNumberType type = PhoneNumberType.PRIMARY;
    private LocalDate createdOn;

    public PhoneNumber(String countryCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public boolean isValid() {
        PhoneNumberSpecification phoneNumberSpecification = new PhoneNumberSpecification();
        return phoneNumberSpecification.isSatisfiedBy(this);
    }
}

