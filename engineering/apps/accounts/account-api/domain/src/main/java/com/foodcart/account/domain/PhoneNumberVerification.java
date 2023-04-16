package com.foodcart.account.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class PhoneNumberVerification {
    private String otp;
    private LocalDateTime otpCreatedOn;
    private boolean verified = false;

    public boolean verify(String otp) {
        //TODO: does logic like this belong Spec??
        LocalDateTime validHoursAgo = LocalDateTime.now().minus(2, ChronoUnit.HOURS);
        return otpCreatedOn.isAfter(validHoursAgo) && this.otp.equals(otp);
    }
}
