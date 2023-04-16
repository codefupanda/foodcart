package com.foodcart.account.entity;

import com.foodcart.account.domain.Address;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "location_lat")
    private BigDecimal locationLat;
    @Column(name = "location_long")
    private BigDecimal locationLong;
    @Column(name = "created_on")
    private LocalDate createdOn;

    public com.foodcart.account.domain.Address toDomain() {
        com.foodcart.account.domain.Address domainAddress = new com.foodcart.account.domain.Address();
        domainAddress.setId(com.foodcart.account.domain.Id.fromId(this.getId()));
        domainAddress.setAddress1(this.getAddress1());
        domainAddress.setAddress2(this.getAddress2());
        domainAddress.setAddress3(this.getAddress3());
        domainAddress.setCity(this.getCity());
        domainAddress.setCountry(this.getCountry());
        domainAddress.setPostalCode(this.getPostalCode());
        domainAddress.setState(this.getState());
        domainAddress.setLocation(new com.foodcart.account.domain.Address.Location());
        domainAddress.getLocation().setLatitude(this.getLocationLat());
        domainAddress.getLocation().setLongitude(this.getLocationLong());
        return domainAddress;
    }

    public static AddressEntity fromDomain(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(address.getId().getId());
        addressEntity.setAddress1(address.getAddress1());
        addressEntity.setAddress2(address.getAddress2());
        addressEntity.setAddress3(address.getAddress3());
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setState(address.getState());
        addressEntity.setLocationLat(address.getLocation().getLatitude());
        addressEntity.setLocationLong(address.getLocation().getLongitude());
        return addressEntity;
    }
}
