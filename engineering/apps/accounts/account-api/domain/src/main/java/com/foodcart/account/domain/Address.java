package com.foodcart.account.domain;


import com.foodcart.account.domain.specification.AddressSpecification;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Address {

    private Id id;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Location location;
    private LocalDate createdOn;

    @Data
    public static class Location {
        private BigDecimal latitude;
        private BigDecimal longitude;
    }

    public boolean isValid() {
        AddressSpecification addressSpecification = new AddressSpecification();
        return addressSpecification.isSatisfiedBy(this);
    }
}
