package com.foodcart.account.entity;

import com.foodcart.account.domain.PhoneNumber;
import com.foodcart.account.domain.PhoneNumberType;
import com.foodcart.account.domain.PhoneNumberVerification;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "phone_numbers")
@IdClass(PhoneNumberEntity.PhoneNumberEntityPK.class)
public class PhoneNumberEntity implements Serializable {

    @Id
    @Column(name = "country_code")
    private String countryCode;
    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_created_on")
    private LocalDateTime otpCreatedOn;

    @Column(nullable = false)
    private boolean verified = false;

    @Column(name = "primary_phone_number")
    private boolean primaryPhoneNumber = false;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Embeddable
    public static class PhoneNumberEntityPK implements Serializable {
        private String countryCode;
        private String phoneNumber;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public com.foodcart.account.domain.PhoneNumber toDomain() {
        com.foodcart.account.domain.PhoneNumber phoneNumberEntity = new com.foodcart.account.domain.PhoneNumber(this.countryCode, this.phoneNumber);
        phoneNumberEntity.setType(PhoneNumberType.PRIMARY); //TODO: handle this
        PhoneNumberVerification verification = new PhoneNumberVerification();
        verification.setOtp(this.otp);
        verification.setOtpCreatedOn(otpCreatedOn);
        verification.setVerified(verified);
        phoneNumberEntity.setVerification(verification);
        return phoneNumberEntity;
    }

    public static PhoneNumberEntity fromDomain(PhoneNumber phoneNumber) {
        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setCountryCode(phoneNumber.getCountryCode());
        phoneNumberEntity.setPhoneNumber(phoneNumber.getPhoneNumber());
        phoneNumberEntity.setPrimaryPhoneNumber(true);
        phoneNumberEntity.setVerified(phoneNumber.getVerification().isVerified());
        return phoneNumberEntity;
    }
}

