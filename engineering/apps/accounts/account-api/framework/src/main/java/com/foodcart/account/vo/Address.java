package com.foodcart.account.vo;


import com.foodcart.account.domain.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Address(
        String id,
        String address1,
        String address2,
        String address3,
        String city,
        String state,
        String country,
        String postalCode,
        BigDecimal locationLat,
        BigDecimal locationLong,
        LocalDate createdOn) {

    public static Address fromDomain(com.foodcart.account.domain.Address address) {
        return new Address(address.getId().getId().toString(), address.getAddress1(), address.getAddress2(),
                address.getAddress3(), address.getCity(), address.getState(),
                address.getCountry(), address.getPostalCode(), null, null,
                address.getCreatedOn());
    }

    public com.foodcart.account.domain.Address toDomain() {
        com.foodcart.account.domain.Address domainAddress = new com.foodcart.account.domain.Address();
        domainAddress.setId(Id.fromId(UUID.fromString(this.id())));
        domainAddress.setAddress1(this.address1());
        domainAddress.setAddress2(this.address2());
        domainAddress.setAddress3(this.address3());
        domainAddress.setCity(this.city());
        domainAddress.setCountry(this.country());
        domainAddress.setPostalCode(this.postalCode());
        domainAddress.setState(this.state());
        domainAddress.setLocation(new com.foodcart.account.domain.Address.Location());
        domainAddress.getLocation().setLatitude(this.locationLat());
        domainAddress.getLocation().setLongitude(this.locationLong());
        return domainAddress;
    }
}
